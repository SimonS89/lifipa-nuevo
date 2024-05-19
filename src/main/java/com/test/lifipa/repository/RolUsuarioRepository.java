package com.test.lifipa.repository;

import com.test.lifipa.model.RolUsuario;
import com.test.lifipa.util.enums.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolUsuarioRepository extends JpaRepository<RolUsuario, Long> {
    Optional<RolUsuario> findByRol(Rol rol);
}
