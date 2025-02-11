package br.edu.ifpr.projeto_hospital.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifpr.projeto_hospital.Models.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    Optional<Doctor> findByCrm(String crm);
}
