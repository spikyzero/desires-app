package ua.com.desires.app.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String uploadFile(MultipartFile file, String fileName);

    String getImageBase64(String imageURL);

}