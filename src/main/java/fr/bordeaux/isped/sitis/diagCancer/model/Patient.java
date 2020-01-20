package fr.bordeaux.isped.sitis.diagCancer.model;

import lombok.*;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//`PatientID`, `NumPatient`, `Sexe`, `DateNaissance`, `Prenom`, `Nom`)

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "Patient")
@Indexed
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NonNull
    private Long numPatient;

    @NotNull
    @NonNull
    private Integer sexe;

    @NotNull
    @NonNull
    private java.sql.Date dateNaissance;

    @NotNull
    @NonNull
    @Size(max=100)
    @Field(termVector = TermVector.YES)
    private String prenom;

    @NotNull
    @NonNull
    @Size(max=100)
    @Field(termVector = TermVector.YES)
    private String nom;

    @NotNull
    @NonNull
    private Trait trait;

}

