package com.gosiaProject.GosiaProject.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Component
public class ImageService {

    private static final String IMAGE_DIRECTORY = "src/main/resources/static/pictures";
    private static String currentImage;

    //@Scheduled(cron = "0 0 9 * * *") // Wykonuje się codziennie o 9:00
    @Scheduled(cron = "*/2 * * * * *")
    public void updateDailyImage() {
        List<String> allImages = loadAllImages();
        if (!allImages.isEmpty()) {
            currentImage = allImages.get(ThreadLocalRandom.current().nextInt(allImages.size()));
            if(currentImage.endsWith(".HEIC") || currentImage.endsWith(".JPG")) currentImage = convertToJpeg(currentImage);
        }
    }

    private List<String> loadAllImages() {
        try {
            return Files.list(Paths.get(IMAGE_DIRECTORY))
                    .map(path -> path.getFileName().toString())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            // Obsługa błędów, np. logowanie
            return Collections.emptyList();
        }
    }
    public String getCurrentImage() {
        return currentImage;
    }

    public String convertToJpeg(String filename) {
        if (filename == null || filename.isEmpty()) {
            return filename;
        }

        if (filename.toLowerCase().endsWith(".heic") || filename.toLowerCase().endsWith(".jpg")) {
            return filename.substring(0, filename.lastIndexOf('.')) + ".jpeg";
        }

        return filename;
    }
}
