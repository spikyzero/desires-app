package ua.com.desires.app.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.desires.app.service.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

@Service
public class DefaultFileService implements FileService {

    private static final String UPLOAD_DIR = "uploads/images/";

    @Override
    public String uploadFile(MultipartFile file, String fileName) {
        try {
            checkDirectoryExists();
            String uniqueFileName = generateUniqueFileName(fileName, Objects.requireNonNull(file.getOriginalFilename()));
            Path filePath = Paths.get(UPLOAD_DIR, uniqueFileName);
            Files.write(filePath, file.getBytes());
            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    @Override
    public String getImageBase64(String imageURL) {
        try {
            Path filePath = Paths.get(imageURL);
            byte[] fileBytes = Files.readAllBytes(filePath);
            String mimeType = Files.probeContentType(filePath);
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            String base64Image = Base64.getEncoder().encodeToString(fileBytes);
            return "data:" + mimeType + ";base64," + base64Image;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file", e);
        }
    }

    private void checkDirectoryExists() throws IOException {
        Path uploadDirPath = Paths.get(UPLOAD_DIR);
        if (Files.notExists(uploadDirPath)) {
            Files.createDirectories(uploadDirPath);
        }
    }

    private String generateUniqueFileName(String fileName, String originalFileName) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uniqueName = fileName + "-" + UUID.randomUUID();
        return uniqueName + extension;
    }

}