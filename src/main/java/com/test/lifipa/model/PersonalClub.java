package com.test.lifipa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalClub extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", referencedColumnName = "id", nullable = false)
    private Club club;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcion_id", referencedColumnName = "id", nullable = false)
    private Funcion funcion;
    private boolean eliminado;
}
