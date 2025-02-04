package br.edu.ifpr.projeto_hospital.Models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "medicine_prescription")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MedicinePrescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int medicinePrescriptionId;

    @Column(nullable = false)
    private String drugName;

    @Column(nullable = false)
    private String dosage;

    @Column(nullable = false)
    private String quantity;

    @Column(nullable = false, columnDefinition = "TEXT") 
    private String wayOfAdministration;

    @Column(columnDefinition = "TEXT")
    private String observation;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false) // Creates a foreign key doctor_id
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
}
