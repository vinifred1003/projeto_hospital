package br.edu.ifpr.projeto_hospital.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpr.projeto_hospital.Repository.DoctorRepository;
import br.edu.ifpr.projeto_hospital.Models.Doctor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/doctor-page")
public class DoctorController {

    @Autowired
  private DoctorRepository doctorRepository;

  @GetMapping("/doctorByCrm")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<?> getDoctorByCrm(@RequestParam(required = false) String crm) {
        Optional<Doctor> doctor = doctorRepository.findByCrm(crm);
        if (doctor.isPresent()) {
            return ResponseEntity.ok(doctor.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado");
        }
    }

}