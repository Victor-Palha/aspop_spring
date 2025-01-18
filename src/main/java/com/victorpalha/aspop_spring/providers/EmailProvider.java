package com.victorpalha.aspop_spring.providers;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailProvider {

    private final JavaMailSender mailSender;
    public EmailProvider(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public boolean sendEmail(String to, String pass, String who) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(new InternetAddress("para.aspop@gmail.com"));
            helper.setTo(new InternetAddress(to));
            helper.setSubject("Sua solicitação foi aprovada! Confira sua senha de acesso.");

            String htmlContent = String.format(
                    """
                    <h1>Olá, %s!</h1>
                    <p>Sua solicitação foi aprovada! Confira sua senha de acesso: <strong>%s</strong></p>
                    <p>Utilize seu CPF e a senha acima para acessar o nosso site e ter acesso aos documentos da organização!</p>
                    <a href="https://aspop-pa.com">https://aspop-pa.com</a>
                    <p>Atenciosamente, <br> Equipe da Associação de Peritos Oficiais do Pará</p>
                    """, who, pass
            );

            String textContent = String.format(
                    "Olá, %s! Sua solicitação foi aprovada! Confira sua senha de acesso: %s. " +
                            "Utilize seu CPF e a senha acima para acessar o nosso site e ter acesso aos documentos da organização! " +
                            "https://aspop-pa.com. Atenciosamente, Equipe da Associação de Peritos Oficiais do Pará",
                    who, pass
            );

            helper.setText(textContent, htmlContent);
            mailSender.send(mimeMessage);

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}