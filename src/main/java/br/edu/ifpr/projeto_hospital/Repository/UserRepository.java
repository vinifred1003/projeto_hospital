package br.edu.ifpr.projeto_hospital.Repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifpr.projeto_hospital.Models.User;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
