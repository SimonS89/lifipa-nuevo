package com.test.lifipa.service;

import com.test.lifipa.exception.ResourceNotFoundException;
import com.test.lifipa.model.Categoria;
import com.test.lifipa.model.Genero;

import java.util.List;

public interface CategoriaService {
    List<Categoria> findByGeneros(List<Genero> generos) throws ResourceNotFoundException;

    void defaultData() throws ResourceNotFoundException;
}
