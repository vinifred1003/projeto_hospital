package br.edu.ifpr.projeto_hospital.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "health_insurance")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class HealthInsurance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String authorizedDrugsName;

    @Column(nullable = false, unique=true)
    private String authorizedDrugsCode;
}
