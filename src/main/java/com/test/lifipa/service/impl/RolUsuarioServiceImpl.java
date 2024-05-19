package com.test.lifipa.service.impl;

import com.test.lifipa.exception.ResourceNotFoundException;
import com.test.lifipa.model.RolUsuario;
import com.test.lifipa.repository.RolUsuarioRepository;
import com.test.lifipa.service.RolUsuarioService;
import com.test.lifipa.util.enums.Rol;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RolUsuarioServiceImpl implements RolUsuarioService {

    private final RolUsuarioRepository rolRepository;

    public RolUsuarioServiceImpl(RolUsuarioRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public void defaultData() {
        if (rolRepository.count() == 0) {
            Arrays.stream(Rol.values()).forEach(rol -> rolRepository.save(new RolUsuario(rol)));
        }
    }

    @Override
    public RolUsuario buscarPorRol(Rol rol) throws ResourceNotFoundException {
        return rolRepository.findByRol(rol).orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));
    }
}
