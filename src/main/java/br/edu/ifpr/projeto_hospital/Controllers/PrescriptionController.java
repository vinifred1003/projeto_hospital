package br.edu.ifpr.projeto_hospital.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import br.edu.ifpr.projeto_hospital.Repository.DoctorRepository;
import br.edu.ifpr.projeto_hospital.Repository.PatientRepository;
import br.edu.ifpr.projeto_hospital.Repository.PrescriptionRepository;

import br.edu.ifpr.projeto_hospital.Models.Doctor;
import br.edu.ifpr.projeto_hospital.Models.MedicinePrescription;
import br.edu.ifpr.projeto_hospital.Models.Patient;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

@RestController
@RequestMapping("/doctor-page/medicine-prescription")
public class PrescriptionController {
    
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
     public @ResponseBody Iterable<MedicinePrescription> getAllPrescriptions() {
        
        return prescriptionRepository.findAll();
    }

    @PostMapping
    public @ResponseBody Map<String, Object> addNewMedicinePrescription(
            @RequestBody MedicinePrescription prescription) {

        Map<String, Object> response = new HashMap<>();
        // Verifica se o paciente foi enviado no corpo da requisição
        if (prescription.getPatient() == null || prescription.getPatient().getPatientId() == null) {
            response.put("error", "Patient information is missing.");
            return response;
        }

        // Verifica se o médico foi enviado no corpo da requisição
        if (prescription.getDoctor() == null || prescription.getDoctor().getDoctorId() == null) {
            response.put("error", "Doctor information is missing.");
            return response;
        }

        Optional<Patient> patient = patientRepository.findById(prescription.getPatient().getPatientId());

        Optional<Doctor> doctor = doctorRepository.findById(prescription.getDoctor().getDoctorId());

        if (patient.isEmpty()) {
            response.put("error", "Patient not found.");
            return response;
        }

        // Verifica se o médico existe no banco de dados
        if (doctor.isEmpty()) {
            response.put("error", "Doctor not found.");
            return response;
        }

        MedicinePrescription mp = new MedicinePrescription();
        mp.setDrugName(prescription.getDrugName());
        mp.setDosage(prescription.getDosage());
        mp.setQuantity(prescription.getQuantity());
        mp.setWayOfAdministration(prescription.getWayOfAdministration());
        mp.setObservation(prescription.getObservation());
        mp.setDoctor(prescription.getDoctor());
        mp.setObservation(prescription.getObservation());
        
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

        prescriptionData.put("drugName", prescription.getDrugName());
        prescriptionData.put("dosage", prescription.getDosage());
        prescriptionData.put("quantity", prescription.getQuantity());
        prescriptionData.put("wayOfAdministration", prescription.getWayOfAdministration());
        prescriptionData.put("observation", prescription.getObservation());

        return prescriptionData;
    }

    @DeleteMapping("/{id}")
    public @ResponseBody long deleteprescription(@PathVariable Long id) {
        Optional<MedicinePrescription> mp = prescriptionRepository.findById(id);

        if (mp.isPresent()) {
            // Exclui o registro no banco de dados
            prescriptionRepository.delete(mp.get());
        } else {
            throw new IllegalArgumentException("Prescription not found with ID: " + id);
        }

        return id;
    }

    @PutMapping("/{id}")
    public @ResponseBody Map<String, Object> updateMedicinePrescription(
            @PathVariable Long id, @RequestBody MedicinePrescription updatedPrescription) {

        Map<String, Object> response = new HashMap<>();

        // Verifica se a prescrição existe no banco
        Optional<MedicinePrescription> existingPrescriptionOpt = prescriptionRepository.findById(id);
        if (existingPrescriptionOpt.isEmpty()) {
            response.put("error", "Prescription not found.");
            return response;
        }

        // Verifica se o paciente foi enviado no corpo da requisição
        if (updatedPrescription.getPatient() == null || updatedPrescription.getPatient().getPatientId() == null) {
            response.put("error", "Patient information is missing.");
            return response;
        }

        // Verifica se o médico foi enviado no corpo da requisição
        if (updatedPrescription.getDoctor() == null || updatedPrescription.getDoctor().getDoctorId() == null) {
            response.put("error", "Doctor information is missing.");
            return response;
        }

        Optional<Patient> patient = patientRepository.findById(updatedPrescription.getPatient().getPatientId());
        Optional<Doctor> doctor = doctorRepository.findById(updatedPrescription.getDoctor().getDoctorId());

        // Verifica se o paciente existe
        if (patient.isEmpty()) {
            response.put("error", "Patient not found.");
            return response;
        }

        // Verifica se o médico existe
        if (doctor.isEmpty()) {
            response.put("error", "Doctor not found.");
            return response;
        }

        // Atualiza os campos da prescrição existente
        MedicinePrescription existingPrescription = existingPrescriptionOpt.get();
        existingPrescription.setDrugName(updatedPrescription.getDrugName());
        existingPrescription.setDosage(updatedPrescription.getDosage());
        existingPrescription.setQuantity(updatedPrescription.getQuantity());
        existingPrescription.setWayOfAdministration(updatedPrescription.getWayOfAdministration());
        existingPrescription.setObservation(updatedPrescription.getObservation());
        existingPrescription.setDoctor(doctor.get());
        existingPrescription.setPatient(patient.get());

        prescriptionRepository.save(existingPrescription);

        // Cria a resposta detalhada
        Map<String, Object> prescriptionData = new HashMap<>();

        prescriptionData.put("id", existingPrescription.getMedicinePrescriptionId());
        prescriptionData.put("drugName", existingPrescription.getDrugName());
        prescriptionData.put("dosage", existingPrescription.getDosage());
        prescriptionData.put("quantity", existingPrescription.getQuantity());
        prescriptionData.put("wayOfAdministration", existingPrescription.getWayOfAdministration());
        prescriptionData.put("observation", existingPrescription.getObservation());

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

        return prescriptionData;
    }

}
