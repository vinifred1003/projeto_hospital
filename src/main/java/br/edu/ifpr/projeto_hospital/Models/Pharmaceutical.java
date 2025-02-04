package br.edu.ifpr.projeto_hospital.Models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

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
