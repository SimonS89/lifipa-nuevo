package com.test.lifipa.controller;

import com.test.lifipa.dto.response.UsuarioResponseDTO;
import com.test.lifipa.exception.ResourceNotFoundException;
import com.test.lifipa.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/v1/admin")
public class AdminController {

    private final UsuarioService usuarioService;

    public AdminController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuarios")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerUsuarios() throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuarios());
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
