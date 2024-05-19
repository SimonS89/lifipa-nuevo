package com.test.lifipa.dto.response;

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
public class UsuarioResponseDTO {
    private Long id;
    private String username;
    private String email;
    private List<RolUsuario> roles;
}
