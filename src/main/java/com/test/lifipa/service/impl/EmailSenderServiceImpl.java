package com.test.lifipa.service.impl;

import com.test.lifipa.exception.ResourceNotFoundException;
import com.test.lifipa.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String emailDesde;
    private final JavaMailSender mailSender;

    public EmailSenderServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public String enviarMail(String to, String subject, String body) throws ResourceNotFoundException {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(emailDesde);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);
            mailSender.send(mimeMessage);
        } catch (Exception ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
        return "Email enviado con exito";
    }

    @Override
    public String msgBienvenida(String username) {
        return "Hola " + username + ",\n \n" + "Para iniciar sesión click aqui : http://localhost:4200/" + "\n \n" + "Saludos, ASJ servicios";
    }

    @Override
    public String msgRecuperarContrasenia(String username,String hashedUsername) {
        return "Hola " + username + ",\n \n" + "Para continuar con el restablecimiento de su contraseña haga click aquí: http://localhost:4200/recuperar_pass/" + hashedUsername + "\n \n" + "Si no has solicitado el restablecimiento descarta este correo. " + "\n \n" + "Saludos, ASJ servicios.";
    }

    @Override
    public String msgResetearContrasenia(String username, String newPassword) {
        return "Hola " + username + ",\n \n" + "Restablecimiento de contraseña exitoso." + "\n \n" + "Tu nueva contraseña es :  " + newPassword + "\n \n" + "Saludos, ASJ servicios.";
    }

    @Override
    public String actualizarContrasenia(String username) {
        return "Hola " + username + ",\n \n" + "Tu contraseña se ha actualizado exitosamente." + "\n \n"  + "Saludos, ASJ servicios.";
    }
}
