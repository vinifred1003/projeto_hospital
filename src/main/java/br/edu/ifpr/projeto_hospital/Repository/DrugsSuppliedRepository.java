package br.edu.ifpr.projeto_hospital.Repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifpr.projeto_hospital.Models.ControlledDrugsSupplied;

public interface DrugsSuppliedRepository extends CrudRepository<ControlledDrugsSupplied, Long> {
    
}
