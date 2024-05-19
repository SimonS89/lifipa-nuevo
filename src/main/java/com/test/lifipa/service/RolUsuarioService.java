package com.test.lifipa.service;

import com.test.lifipa.exception.ResourceNotFoundException;
import com.test.lifipa.model.RolUsuario;
import com.test.lifipa.util.enums.Rol;

public interface RolUsuarioService {
    void defaultData();

    RolUsuario buscarPorRol(Rol rol) throws ResourceNotFoundException;
}
