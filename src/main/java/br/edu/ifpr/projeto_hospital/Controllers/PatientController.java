package br.edu.ifpr.projeto_hospital.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpr.projeto_hospital.Models.HealthInsurance;
import br.edu.ifpr.projeto_hospital.Models.Patient;
import br.edu.ifpr.projeto_hospital.Repository.InsuranceRepository;
import br.edu.ifpr.projeto_hospital.Repository.PatientRepository;

@RestController
@RequestMapping("/doctor-page/patient")
public class PatientController {
    
    
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public @ResponseBody Iterable<Patient> getAllPrescriptions() {

        return patientRepository.findAll();
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public @ResponseBody String addNewPatient(@RequestBody Patient patient) {
        try {
            Patient p = new Patient();

            p.setName(patient.getName());
            p.setCpf(patient.getCpf());
            p.setBirthdate(patient.getBirthdate());

            // Atualiza o HealthInsurance somente se ele foi enviado no corpo da requisição
            if (patient.getHealthInsurance() != null &&
                    patient.getHealthInsurance().getHealthInsuranceDrugId() != null) {

                Optional<HealthInsurance> HI = insuranceRepository.findById(
                        patient.getHealthInsurance().getHealthInsuranceDrugId());

                if (HI.isPresent()) {
                    p.setHealthInsurance(HI.get());
                } else {
                    return "HealthInsurance with ID " + patient.getHealthInsurance().getHealthInsuranceDrugId()
                            + " not found.";
                }
            } else {
                // Se não foi enviado, permite que o campo seja nulo
                p.setHealthInsurance(null);
            }
            patientRepository.save(p);

            return "Patient inserted with success";
        } catch (Error e) {
            return "Houve algum erro" + e;
        }
    }

    @PutMapping("/{id}")
    public @ResponseBody String updatePatient(@PathVariable Long id, @RequestBody Patient updatePatient) {
        try {

            Optional<Patient> patientOpt = patientRepository.findById(id);
            Patient p = patientOpt.get();

            p.setName(updatePatient.getName());
            p.setCpf(updatePatient.getCpf());
            p.setBirthdate(updatePatient.getBirthdate());

            // Atualiza o HealthInsurance somente se ele foi enviado no corpo da requisição
            if (updatePatient.getHealthInsurance() != null &&
                    updatePatient.getHealthInsurance().getHealthInsuranceDrugId() != null) {

                Optional<HealthInsurance> HI = insuranceRepository.findById(
                        updatePatient.getHealthInsurance().getHealthInsuranceDrugId());

                if (HI.isPresent()) {
                    p.setHealthInsurance(HI.get());
                } else {
                    return "HealthInsurance with ID " + updatePatient.getHealthInsurance().getHealthInsuranceDrugId()
                            + " not found.";
                }
            } else {
                // Se não foi enviado, permite que o campo seja nulo
                p.setHealthInsurance(null);
            }
            patientRepository.save(p);

            return "Patient " + id + " updated with success";
        } catch (Error e) {
            return "Houve algum erro" + e;
        }

    }

    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable Long id) {
        Optional<Patient> p = patientRepository.findById(id);

        if (p.isPresent()) {
            // Exclui o registro no banco de dados
            patientRepository.delete(p.get());
        } else {
            throw new IllegalArgumentException("Prescription not found with ID: " + id);
        }

        return "Patient " + id + " deleted with success";
    }

}
