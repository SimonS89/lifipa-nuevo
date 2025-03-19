package com.test.lifipa.service;

import com.test.lifipa.dto.request.ClubRequestDTO;
import com.test.lifipa.dto.response.ClubResponseDTO;
import com.test.lifipa.exception.ResourceNotFoundException;

public interface ClubService {
    ClubResponseDTO crear(ClubRequestDTO clubDTO) throws ResourceNotFoundException;
}
