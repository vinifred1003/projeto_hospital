package br.edu.ifpr.projeto_hospital.Models;

 import jakarta.persistence.Column;
 import jakarta.persistence.Entity;
 import jakarta.persistence.GeneratedValue;
 import jakarta.persistence.GenerationType;
 import jakarta.persistence.Id;
 import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
 

@Entity
@Table(name="Doctor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Doctor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private String digitalSignature;

    @Column(nullable = false, unique=true)
    private String crm;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    
    
}
