package fr.bordeaux.isped.sitis.diagCancer.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String sexe;

    @NotNull
    private Date dateNaissance;

    @NotNull
    @Size(max=100)
    private String prenom;

    @NotNull
    @Size(max=100)
    private String nom;

}
