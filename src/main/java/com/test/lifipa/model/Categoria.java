package com.test.lifipa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaHabilitada;
    @ManyToMany(mappedBy = "categorias", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Club> clubes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genero_id", referencedColumnName = "id", nullable = false)
    private Genero genero;
}
