package br.edu.ifpr.projeto_hospital.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@Table(name = "Pharmaceuticals")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Pharmaceutical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pharmaceuticalId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique=true)
    private String crf;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
