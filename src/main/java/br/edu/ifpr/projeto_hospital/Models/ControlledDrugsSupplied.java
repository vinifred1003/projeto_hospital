package br.edu.ifpr.projeto_hospital.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "controlled_drugs_supplied")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ControlledDrugsSupplied {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long controlledDrugsSuppliedId;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate issuedDate;

    @ManyToOne
    @JoinColumn(name = "drug_id", nullable = false) // Creates a foreign key doctor_id
    private Drug drug;

    @ManyToOne
    @JoinColumn(name = "pharmaceutical_id", nullable = false)
    private Pharmaceutical pharmaceutical;

    @OneToOne
    @JoinColumn(name = "medicinePrescription_id", nullable = false)
    private MedicinePrescription medicinePrescription;
}
