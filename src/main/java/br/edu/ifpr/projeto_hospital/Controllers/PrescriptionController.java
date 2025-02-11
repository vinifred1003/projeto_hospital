package br.edu.ifpr.projeto_hospital.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.edu.ifpr.projeto_hospital.Repository.DoctorRepository;
import br.edu.ifpr.projeto_hospital.Repository.PatientRepository;
import br.edu.ifpr.projeto_hospital.Repository.PrescriptionRepository;
import br.edu.ifpr.projeto_hospital.Models.Doctor;
import br.edu.ifpr.projeto_hospital.Models.MedicinePrescription;
import br.edu.ifpr.projeto_hospital.Models.Patient;
import br.edu.ifpr.projeto_hospital.Models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

@RestController
@RequestMapping("/doctor-page")
public class PrescriptionController {
    
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    private DoctorRepository doctorRepository;

    private PatientRepository patientRepository;

    

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
     public @ResponseBody Iterable<MedicinePrescription> getAllPrescriptions() {
        
        return prescriptionRepository.findAll();
    }

    @GetMapping("/patient-by-cpf")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<?> getPatientByCpf(@RequestParam(required = false) String cpf) {
        Optional<Patient> patientOpt = patientRepository.findByCpf(cpf);
        if (patientOpt.isPresent()) {
            return ResponseEntity.ok(patientOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado");
        }
    }
    @GetMapping("/doctor-by-crm")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<?> getDoctorByCrm(@RequestParam(required = false) String crm) {
        Optional<Doctor> doctorOpt = doctorRepository.findByCrm(crm);
        if (doctorOpt.isPresent()) {
            return ResponseEntity.ok(doctorOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico não encontrado");
        }
    }
    

    @PostMapping
    public @ResponseBody Map<String, Object> addNewMedicinePrescription(
            @RequestBody String drugName, 
            @RequestBody String dosage,
            @RequestBody String quantity, 
            @RequestBody String wayOfAdministration,
            @RequestBody String observation, 
            @RequestBody String cpf, 
            @RequestBody String crm) {

        
        Optional<Patient> patient = patientRepository.findByCpf(cpf);
        
        Optional<Doctor> doctor = doctorRepository.findByCrm(crm);

        MedicinePrescription mp = new MedicinePrescription();
        mp.setDrugName(drugName);
        mp.setDosage(dosage);
        mp.setQuantity(quantity);
        mp.setWayOfAdministration(wayOfAdministration);
        mp.setObservation(observation);
        
        if(doctor.isPresent()){
            mp.setDoctor(doctor.get());
        }
        if(patient.isPresent()){
            mp.setPatient(patient.get());
        }
        prescriptionRepository.save(mp);

        Map<String, Object> prescriptionData = new HashMap<>();
        
        if (patient.isPresent()) {
            Patient p = patient.get();
            prescriptionData.put("patientName", p.getName());
            prescriptionData.put("birthdate", p.getBirthdate());
            prescriptionData.put("cpf", p.getCpf());
            prescriptionData.put("healthInsurance", p.getHealthInsurance());
        }

        if (doctor.isPresent()) {
            Doctor d = doctor.get();
            prescriptionData.put("crm", d.getCrm());
            prescriptionData.put("digitalSignature", d.getDigitalSignature());
        }

        prescriptionData.put("drugName", drugName);
        prescriptionData.put("dosage", dosage);
        prescriptionData.put("quantity", quantity);
        prescriptionData.put("wayOfAdministration", wayOfAdministration);
        prescriptionData.put("observation", observation);

        return prescriptionData;
    }
        
}
