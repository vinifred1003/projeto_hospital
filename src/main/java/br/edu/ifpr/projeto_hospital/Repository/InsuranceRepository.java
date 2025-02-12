package br.edu.ifpr.projeto_hospital.Repository;

import org.springframework.data.repository.CrudRepository;


import br.edu.ifpr.projeto_hospital.Models.HealthInsurance;

public interface InsuranceRepository extends CrudRepository<HealthInsurance, Long> {
    
}
