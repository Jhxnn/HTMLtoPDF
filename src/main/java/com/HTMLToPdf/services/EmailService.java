package com.HTMLToPdf.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public String enviarEmailComPdf(String destinatario, String assunto, String mensagem, byte[] pdfBytes, String nomeArquivo) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            // true = multipart
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(remetente);
            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(mensagem);

            // Anexa o PDF
            helper.addAttachment(nomeArquivo, new ByteArrayResource(pdfBytes));

            javaMailSender.send(mimeMessage);
            return "Email enviado com sucesso";
        } catch (MessagingException e) {
            return "Erro ao enviar e-mail com PDF: " + e.getMessage();
        }
    }
}
