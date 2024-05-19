package com.test.lifipa.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.test.lifipa.model.RolUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutenticacionUsuarioResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String token;
    private List<RolUsuario> roles;
}
