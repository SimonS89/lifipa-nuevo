package com.test.lifipa.service.impl;

import com.test.lifipa.dto.request.ClubRequestDTO;
import com.test.lifipa.dto.response.ClubResponseDTO;
import com.test.lifipa.exception.ResourceNotFoundException;
import com.test.lifipa.model.Club;
import com.test.lifipa.model.Genero;
import com.test.lifipa.repository.ClubRepository;
import com.test.lifipa.service.CategoriaService;
import com.test.lifipa.service.ClubService;
import com.test.lifipa.service.GeneroService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    private final Logger logger = LoggerFactory.getLogger((ClubServiceImpl.class));
    private final ModelMapper mapper;
    private final ClubRepository clubRepository;
    private final CategoriaService categoriaService;
    private final GeneroService generoService;

    public ClubServiceImpl(ModelMapper mapper, ClubRepository clubRepository, CategoriaService categoriaService, GeneroService generoService) {
        this.mapper = mapper;
        this.clubRepository = clubRepository;
        this.categoriaService = categoriaService;
        this.generoService = generoService;
    }

    @Override
    public ClubResponseDTO crear(ClubRequestDTO clubDTO) throws ResourceNotFoundException {
        Club club = mapper.map(clubDTO, Club.class);
        List<Genero> generos = new ArrayList<>();
        for (Genero genero : club.getGeneros()) generos.add(generoService.buscarPorGenero(genero.getNombre()));
        club.setGeneros(generos);
        club.setCategorias(categoriaService.findByGeneros(club.getGeneros()));
        return mapper.map(clubRepository.save(club), ClubResponseDTO.class);
    }
}
