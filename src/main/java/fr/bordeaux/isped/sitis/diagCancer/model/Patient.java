package fr.bordeaux.isped.sitis.diagCancer.model;

import lombok.*;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

//`PatientID`, `NumPatient`, `Sexe`, `DateNaissance`, `Prenom`, `Nom`)

@Data

@Entity
@Table(name = "Patient")
@Indexed
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long numPatient;

    @NotNull
    private String sexe;

    @NotNull
    private java.sql.Date dateNaissance;

    @NotNull
    @Size(max=100)
    @Field
    private String prenom;

    @NotNull
    @Size(max=100)
    @Field
    private String nom;

}
