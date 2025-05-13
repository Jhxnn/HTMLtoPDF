package com.HTMLToPdf.dtos;

import org.springframework.web.multipart.MultipartFile;

public record FileDto(MultipartFile file, String email) {
}
