package br.edu.ifpr.projeto_hospital.Repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifpr.projeto_hospital.Models.Pharmaceutical;

public interface PharmaceuticalRepository extends CrudRepository<Pharmaceutical, Long> {
    
}
