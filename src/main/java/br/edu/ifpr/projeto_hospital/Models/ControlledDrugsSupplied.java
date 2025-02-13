package br.edu.ifpr.projeto_hospital.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "controlledDrugsSupplied", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DrugSupplied> drugSuppliedList = new ArrayList<>();

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDateTime issuedDate;

    @ManyToOne
    @JoinColumn(name = "pharmaceutical_id", nullable = false)
    private Pharmaceutical pharmaceutical;


}
