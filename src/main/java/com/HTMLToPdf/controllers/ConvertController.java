package com.HTMLToPdf.controllers;


import com.HTMLToPdf.dtos.FileDto;
import com.HTMLToPdf.services.ConvertService;
import com.HTMLToPdf.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @GetMapping
    public ResponseEntity<String> enviarEmail(@RequestBody FileDto fileDto) throws IOException {
        byte[] pdf = convertService.convertHtmlFileToPdf(fileDto.file());
        emailService.enviarEmailComPdf(fileDto.email(),
                "Registro de Ponto",
                "Segue registro de ponto em PDF",
                pdf,
                "registro");
        return ResponseEntity.status(HttpStatus.OK).body("Arquivo convertido e enviado para: " + fileDto.email());
    }
}
