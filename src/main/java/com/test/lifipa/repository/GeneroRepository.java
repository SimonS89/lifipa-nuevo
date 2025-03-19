package com.test.lifipa.repository;

import com.test.lifipa.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeneroRepository extends JpaRepository<Genero,Long> {
    Optional<Genero> findByNombre(String genero);
}
