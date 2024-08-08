package com.test.lifipa.service;

import com.test.lifipa.exception.ResourceNotFoundException;
import com.test.lifipa.model.Genero;

import java.util.List;

public interface GeneroService {
    Genero buscarPorIdInterno(Long id) throws ResourceNotFoundException;

    Genero buscarPorGenero(String genero) throws ResourceNotFoundException;

    List<Genero> listarTodo() throws ResourceNotFoundException;

    void defaultData();
}
