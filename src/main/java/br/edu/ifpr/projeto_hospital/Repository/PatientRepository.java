package br.edu.ifpr.projeto_hospital.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifpr.projeto_hospital.Models.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long> {
    Optional<Patient> findByCpf(String cpf);
}
