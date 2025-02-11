package br.edu.ifpr.projeto_hospital.Repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifpr.projeto_hospital.Models.MedicinePrescription;

public interface PrescriptionRepository extends CrudRepository<MedicinePrescription, Long> {
    
}
