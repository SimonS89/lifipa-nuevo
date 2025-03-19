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
            mimeMessageHelper.setText(body, true);
            mailSender.send(mimeMessage);
        } catch (Exception ex) {
            throw new ResourceNotFoundException(ex.getMessage());
        }
        return "Email enviado con exito";
    }

    // ✅ Método genérico para construir el correo
    private String construirCorreo(String titulo, String mensaje, String linkTexto, String linkUrl) {
        return "<html>" +
                "<head>" +
                "<style>" +
                "  body { font-family: Arial, sans-serif; background-color: #f4f4f4; text-align: center; }" +
                "  .container { max-width: 500px; margin: auto; background: #fff; padding: 20px; border-radius: 10px; box-shadow: 0px 4px 8px rgba(0,0,0,0.2); text-align: center; }" +
                "  h2 { color: #004080; }" +
                "  .button { display: inline-block; padding: 12px 20px; background: #004080; color: white!important; text-decoration: none; border-radius: 5px; font-weight: bold; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "  <img src='https://res.cloudinary.com/greenhome/image/upload/v1742081075/lifipa-fotor-2024061111286_il6wtk.png' alt='LIFIPA Logo' style='display: block; margin: auto; width: 150px;'/>" +
                "  <h2>" + titulo + "</h2>" +
                "  <p>" + mensaje + "</p>" +
                (linkTexto != null ? "<p><a href='" + linkUrl + "' class='button'>" + linkTexto + "</a></p>" : "") +
                "  <p>Saludos,<br/>LIFIPA</p>" +
                "</div>" +
                "</body>" +
                "</html>";
    }

    // ✅ Métodos que llaman a la plantilla con diferentes mensajes

    @Override
    public String msgBienvenida(String username) {
        return construirCorreo("¡Hola, " + username + "!",
                "Bienvenido a LIFIPA. Para acceder a la plataforma, haz clic en el siguiente botón:",
                "Iniciar Sesión", "http://localhost:4200/");
    }

    @Override
    public String msgRecuperarContrasenia(String username, String hashedUsername) {
        return construirCorreo("¡Hola " + username + "!",
                "Para restablecer tu contraseña, haz clic en el siguiente botón:",
                "Restablecer Contraseña", "http://localhost:4200/recuperar_pass/" + hashedUsername);
    }

    @Override
    public String msgResetearContrasenia(String username, String newPassword) {
        return construirCorreo("Hola " + username + "!",
                "Tu contraseña ha sido restablecida con éxito. <br>Tu nueva contraseña es: <strong>" + newPassword + "</strong>",
                null, null);
    }

    @Override
    public String actualizarContrasenia(String username) {
        return construirCorreo("Hola " + username + "!",
                "Tu contraseña se ha actualizado correctamente.",
                null, null);
    }
}
