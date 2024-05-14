package com.demy.controllers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileDownloadController {

	
	@Value("${file.upload-dir}")
	String FILE_DIRECTORY;
	
	
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        // Define the file path
    

        Path filePath = Paths.get(FILE_DIRECTORY).resolve(fileName).normalize();
        System.out.println("kio");
        try {
            // Load file as Resource
            Resource resource = new UrlResource(filePath.toUri());
            
            // Check if the file exists
            if (resource.exists()) {
                // Set Content-Type header
                String contentType = "application/octet-stream"; // Default content type
                try {
                    contentType = Files.probeContentType(filePath);
                } catch (IOException e) {
                    e.printStackTrace(); // Handle exception
                }
                
                // Set Content-Disposition header to trigger download
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
                
                // Return ResponseEntity with the file content
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .headers(headers)
                        .body(resource);
            } else {
                // File not found
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}