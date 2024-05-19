package com.test.lifipa.service;

import com.test.lifipa.exception.ResourceNotFoundException;

public interface EmailService {
    String enviarMail(String to, String subject, String body) throws ResourceNotFoundException;

    String msgBienvenida(String username);

    String msgRecuperarContrasenia(String username,String hashedUsername);

    String msgResetearContrasenia(String username, String newPassword);

    String actualizarContrasenia(String username);
}
