package br.edu.ifpr.projeto_hospital.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifpr.projeto_hospital.Models.Patient;
import br.edu.ifpr.projeto_hospital.Repository.PatientRepository;

public class PatientController {
    
    
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patient")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<?> getPatientByCpf(@RequestParam(required = false) String cpf) {
        Optional<Patient> patientOpt = patientRepository.findByCpf(cpf);
        if (patientOpt.isPresent()) {
            return ResponseEntity.ok(patientOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado");
        }
    }
}
