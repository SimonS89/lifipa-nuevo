package com.test.lifipa.service.impl;

import com.test.lifipa.exception.ResourceNotFoundException;
import com.test.lifipa.model.Categoria;
import com.test.lifipa.model.Genero;
import com.test.lifipa.repository.CategoriaRepository;
import com.test.lifipa.service.CategoriaService;
import com.test.lifipa.service.GeneroService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaService.class);
    private final ModelMapper mapper;
    private final CategoriaRepository categoriaRepository;
    private final GeneroService generoService;

    public CategoriaServiceImpl(ModelMapper mapper, CategoriaRepository categoriaRepository, GeneroService generoService) {
        this.mapper = mapper;
        this.categoriaRepository = categoriaRepository;
        this.generoService = generoService;
    }

    @Override
    public List<Categoria> findByGeneros(List<Genero> generos) throws ResourceNotFoundException {
        List<Long> generoIds = generos.stream().map(Genero::getId).collect(Collectors.toList());
        return categoriaRepository.findByGeneroIdIn(generoIds);
    }

    @Override
    public void defaultData() throws ResourceNotFoundException {
        if (categoriaRepository.count() == 0) {
            List<String> categorias = Arrays.asList("2012", "2012", "2013", "2014", "2015", "2016", "2017", "2018","2019", "SUB-6", "SUB-8", "SUB-10", "SUB-12", "SUB-14", "SUB-17", "PRIMERA", "SENIOR");
            for (String categoria : categorias) {
                Categoria categoriaNueva = new Categoria();
                categoriaNueva.setNombre(categoria);
                if (categoria.startsWith("2")) {
                    categoriaNueva.setGenero(generoService.buscarPorGenero("MASCULINO"));
                    categoriaNueva.setFechaHabilitada(LocalDate.of(Integer.parseInt(categoria), 1, 1));
                } else {
                    categoriaNueva.setGenero(generoService.buscarPorGenero("FEMENINO"));
                    int anio = switch (categoria) {
                        case "SUB-6" -> 2019;
                        case "SUB-8" -> 2017;
                        case "SUB-10" -> 2015;
                        case "SUB-12" -> 2013;
                        case "SUB-14" -> 2011;
                        case "SUB-17" -> 2008;
                        case "PRIMERA" -> 2005;
                        case "SENIOR" -> 2004;
                        default -> throw new ResourceNotFoundException("Categoría no válida");
                    };
                    categoriaNueva.setFechaHabilitada(LocalDate.of(anio, 1, 1));
                }
                categoriaRepository.save(categoriaNueva);
            }
        }
    }
}
