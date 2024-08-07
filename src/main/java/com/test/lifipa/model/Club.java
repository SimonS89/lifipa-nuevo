package com.test.lifipa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clubes")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaFundacion;
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaInscripcion;
    private String calle;
    private String numero;
    private String localidad;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "clubes_categorias", joinColumns = @JoinColumn(name = "club_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    @JsonManagedReference
    private List<Categoria> categorias;
    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
    private List<PersonalClub> personal;
    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
    private List<Jugador> jugadores;
    private boolean eliminado;
}
