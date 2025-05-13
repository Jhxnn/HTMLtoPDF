package com.HTMLToPdf.controllers;


import com.HTMLToPdf.services.ConvertService;
import com.HTMLToPdf.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/convert")
public class ConvertController {

    @Autowired
    EmailService emailService;

    @Autowired
    ConvertService convertService;

    @PostMapping
    public ResponseEntity<String> enviarEmail(@RequestParam("file") MultipartFile file,
                                              @RequestParam("email") String email) throws IOException {
        byte[] pdf = convertService.convertHtmlFileToPdf(file);
        emailService.enviarEmailComPdf(email,
                "Comprovante de Ponto",
                "Segue comprovante de ponto em formato PDF",
                pdf,
                "comprovante.pdf");
        return ResponseEntity.status(HttpStatus.OK).body("Arquivo convertido e enviado para: " + email);
    }
}
