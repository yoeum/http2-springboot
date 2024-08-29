package com.example.h2c;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class FileController {

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam String file) throws IOException {
        File requestedFile = new File(file);  // 애플리케이션과 같은 디렉토리에서 파일을 찾음

        if (!requestedFile.exists() || requestedFile.isDirectory()) {
            return ResponseEntity.notFound().build();
        }

        FileInputStream fileInputStream = new FileInputStream(requestedFile);
        InputStreamResource resource = new InputStreamResource(fileInputStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + requestedFile.getName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
