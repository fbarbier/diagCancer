package fr.bordeaux.isped.sitis.diagCancer.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data

@Entity
@Table(name = "diagnostics")

public class Das {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long das;
}
