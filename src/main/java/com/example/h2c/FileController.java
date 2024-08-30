package com.example.h2c;


import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

@RestController
public class FileController {

   @GetMapping("/download")
    public void downloadFile(@RequestParam String file, HttpServletResponse response) throws IOException {
        // 파일 경로 지정 (예: images/image1.jpg)
        ClassPathResource imgFile = new ClassPathResource(file);

        response.setContentType("image/jpeg");
        try (InputStream in = imgFile.getInputStream()) {
            IOUtils.copy(in, response.getOutputStream());
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("File Not Found");
        }
    }
      // /hello URL을 요청하면 "hi"를 응답
    @GetMapping("/hello")
    public String sayHello() {
        return "hi";
    }
}
