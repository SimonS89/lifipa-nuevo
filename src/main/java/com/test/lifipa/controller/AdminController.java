package com.test.lifipa.controller;

import com.test.lifipa.dto.response.UsuarioResponseDTO;
import com.test.lifipa.exception.ResourceNotFoundException;
import com.test.lifipa.service.UsuarioService;
import com.test.lifipa.util.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/v1/admin")
public class AdminController {

    private final UsuarioService usuarioService;
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    public AdminController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuarios")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerUsuarios() throws ResourceNotFoundException {
//        logger.info("Iniciando solicitud para obtener la lista de usuarios.");

        List<UsuarioResponseDTO> usuarios = usuarioService.listarUsuarios();

//        logger.info("Lista de usuarios obtenida: \n{}", JsonUtil.toJson(usuarios));

        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }



    @GetMapping("/usuarios/rol/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UsuarioResponseDTO> asignarOQuitarRolAdmin(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.asignarOQuitarRolAdmin(id));
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, String>> eliminarUsuario(@PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.eliminarUsuario(id));
    }
}
