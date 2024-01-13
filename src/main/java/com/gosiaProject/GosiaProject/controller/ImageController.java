package com.gosiaProject.GosiaProject.controller;

import com.gosiaProject.GosiaProject.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<Resource> getImageOfTheDay() throws IOException {
        Path imagePath = Paths.get("src/main/resources/static/pictures/" + imageService.getCurrentImage());
        Resource image = new UrlResource(imagePath.toUri());
        if (image.exists() || image.isReadable()) {
            return ResponseEntity.ok().body(image);
        } else {
            return ResponseEntity.ok().build();
        }
    }
}
