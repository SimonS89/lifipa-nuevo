package com.test.lifipa.repository;

import com.test.lifipa.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findByGeneroIdIn(List<Long> generoIds);
}
