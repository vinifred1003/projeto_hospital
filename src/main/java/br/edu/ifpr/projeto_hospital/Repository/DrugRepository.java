package br.edu.ifpr.projeto_hospital.Repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifpr.projeto_hospital.Models.Drug;

public interface DrugRepository extends CrudRepository<Drug, Long> {
    
}
