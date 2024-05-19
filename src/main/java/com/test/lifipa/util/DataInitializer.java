package com.test.lifipa.util;

import com.test.lifipa.exception.ResourceNotFoundException;
import com.test.lifipa.service.RolUsuarioService;
import com.test.lifipa.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final RolUsuarioService rolService;
    private final UsuarioService usuarioService;
    Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    public DataInitializer(RolUsuarioService rolService, UsuarioService usuarioService) {
        this.rolService = rolService;
        this.usuarioService = usuarioService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void dataInitializer() throws ResourceNotFoundException {
        logger.info("Inicializando aplicacion, llenando BD");
        logger.info("Creando Roles de usuario");
        rolService.defaultData();
        logger.info("Creando usuario Admin");
        usuarioService.defaultAdmin();
    }
}
