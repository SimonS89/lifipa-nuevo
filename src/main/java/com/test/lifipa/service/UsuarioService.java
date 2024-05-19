package com.test.lifipa.service;


import com.test.lifipa.dto.request.ActualizarPasswordRequestDTO;
import com.test.lifipa.dto.request.UsuarioRegistroRequestDTO;
import com.test.lifipa.dto.response.AutenticacionUsuarioResponseDTO;
import com.test.lifipa.dto.response.UsuarioResponseDTO;
import com.test.lifipa.exception.AlreadyExistsException;
import com.test.lifipa.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Map;

public interface UsuarioService {
    void defaultAdmin() throws ResourceNotFoundException;

    UsuarioResponseDTO crearUsuario(UsuarioRegistroRequestDTO userRegisterRequestDTO) throws ResourceNotFoundException, AlreadyExistsException;

    UsuarioResponseDTO buscarPorId(Long id) throws ResourceNotFoundException;

    List<UsuarioResponseDTO> listarUsuarios() throws ResourceNotFoundException;

    void eliminadoLogico(Long id) throws ResourceNotFoundException;

    Map<String, String> eliminarUsuario(Long id) throws ResourceNotFoundException;

    UsuarioResponseDTO asignarOQuitarRolAdmin(Long id) throws ResourceNotFoundException;

    Map<String, String> recuperarPassword(String username) throws ResourceNotFoundException;

    Map<String, String> resetearPassword(String hashedUsername) throws ResourceNotFoundException;

    AutenticacionUsuarioResponseDTO findByUsername(String username) throws ResourceNotFoundException;

    Map<String, String> actualizarPassword(ActualizarPasswordRequestDTO actualizarPassRequestDTO, String username) throws ResourceNotFoundException;

    AutenticacionUsuarioResponseDTO autenticarUsuarioYObtenerToken(String username) throws ResourceNotFoundException;

    UsuarioResponseDTO validarUsernameExistente(String username) throws AlreadyExistsException;
}
