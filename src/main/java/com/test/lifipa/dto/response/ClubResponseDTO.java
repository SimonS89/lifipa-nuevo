package com.test.lifipa.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.test.lifipa.model.Categoria;
import com.test.lifipa.model.Genero;
import com.test.lifipa.model.Jugador;
import com.test.lifipa.model.PersonalClub;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClubResponseDTO {

    private Long id;
    private String nombre;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaFundacion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaInscripcion;
    private String calle;
    private String numero;
    private String localidad;
    private List<CategoriaResponseDTO> categorias;
    private List<Genero> generos;
    private boolean eliminado;
}
