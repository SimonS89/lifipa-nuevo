package com.test.lifipa.service.impl;

import com.test.lifipa.exception.ResourceNotFoundException;
import com.test.lifipa.model.Genero;
import com.test.lifipa.repository.GeneroRepository;
import com.test.lifipa.service.GeneroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GeneroServiceImpl implements GeneroService {

    private final GeneroRepository generoRepository;
    private final Logger logger = LoggerFactory.getLogger(GeneroServiceImpl.class);

    public GeneroServiceImpl(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @Override
    public Genero buscarPorIdInterno(Long id) throws ResourceNotFoundException {
        return generoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Género no encontrado"));
    }

    @Override
    public Genero buscarPorGenero(String genero) throws ResourceNotFoundException {
        return generoRepository.findByNombre(genero).orElseThrow(() -> new ResourceNotFoundException("Género no encontrado"));
    }

    @Override
    public List<Genero> listarTodo() throws ResourceNotFoundException {
        List<Genero> generos = generoRepository.findAll();
        if (generos.isEmpty()) throw new ResourceNotFoundException("No hay géneros disponibles");
        return generos;
    }

    @Override
    public void defaultData() {
        if (generoRepository.count() == 0) {
            List<String> generos = new ArrayList<>(Arrays.asList("MASCULINO", "FEMENINO"));
            generoRepository.saveAll(generos.stream().map(Genero::new).toList());
        }
    }
}
