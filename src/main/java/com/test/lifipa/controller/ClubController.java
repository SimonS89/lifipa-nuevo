package com.test.lifipa.controller;

import com.test.lifipa.dto.request.ClubRequestDTO;
import com.test.lifipa.dto.response.ClubResponseDTO;
import com.test.lifipa.exception.ResourceNotFoundException;
import com.test.lifipa.service.ClubService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/app/v1/club")
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping
    public ResponseEntity<ClubResponseDTO> crearClub(@Valid @RequestBody ClubRequestDTO clubDTO) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(clubService.crear(clubDTO));
    }
}
