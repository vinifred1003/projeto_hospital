package br.edu.ifpr.projeto_hospital.Repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifpr.projeto_hospital.Models.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Integer>  {
    
}
