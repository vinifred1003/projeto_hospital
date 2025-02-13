package br.edu.ifpr.projeto_hospital.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseSeeder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        //Tabela papeis
        jdbcTemplate.update("INSERT INTO roles (name) VALUES ('Admin'),('Pharmaceutical'),('Doctor');");

        //Usuário: 12345 --> Senha:543210 || Usuário: 123456 --> Senha: 654321
        jdbcTemplate.update("INSERT INTO Users (username, password) VALUES ('12345', '$2a$10$q/vwY62ZkhdTP4ySrGWQmOgiBRMagyr6MlSRpb76aqw9gTHcj/iiK'),"+
        "('123456', '$2a$10$D8/lc13J9MMlnb/WA886ke1hyD5XpESY9mRyrm5ByIaZeB9XGQu8m');");

        jdbcTemplate.update("INSERT INTO user_roles (user_id, role_id) VALUES (2, 3),(1, 2);");

        jdbcTemplate.update("INSERT INTO doctors (name, digital_signature, crm, user_id) VALUES ('João Silva', 'A886ke1hyD5XpESY', '123456',2);");

        jdbcTemplate.update("INSERT INTO pharmaceuticals (name, crf, user_id) VALUES ('Fabio Melo', '12345',1);");

        jdbcTemplate.update(
                "INSERT INTO health_insurance_drugs (authorized_drugs_code, authorized_drugs_name) VALUES ('12345','dramin');");

        jdbcTemplate
                .update("INSERT INTO drugs (drug_code, drug_name) VALUES ('12345','dramin'), ('3214', 'dorflex');");

        jdbcTemplate.update( "INSERT INTO Patients (name, cpf, birthdate, health_insurance_id) VALUES ('John Doe', '12345678900', '1990-05-15', 1);");

        System.out.println("Banco de Dados inicializado com sucesso!");
    }
}