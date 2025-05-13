package com.HTMLToPdf.services;


import com.lowagie.text.DocumentException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;

@Service
public class ConvertService{

    public byte[] convertHtmlFileToPdf(MultipartFile htmlFile) throws IOException, DocumentException {
        String htmlContent = new String(htmlFile.getBytes(), "UTF-8");

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);
            return outputStream.toByteArray();
        }
    }
}

