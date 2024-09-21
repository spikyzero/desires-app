package ua.com.desires.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.desires.app.model.Desire;
import ua.com.desires.app.repository.DesireRepository;
import ua.com.desires.app.service.DesireService;
import ua.com.desires.app.service.FileService;

import java.util.List;

@Service
public class DefaultDesireService implements DesireService {

    private final DesireRepository desireRepository;
    private final FileService fileService;

    @Autowired
    public DefaultDesireService(DesireRepository desireRepository, FileService fileService) {
        this.desireRepository = desireRepository;
        this.fileService = fileService;
    }

    @Override
    public void saveDesire(Desire desire, MultipartFile image) {
        String fileName = desire.getName().toLowerCase().replace(" ", "-");
        String imagePath = fileService.uploadFile(image, fileName);
        desire.setImageURL(imagePath);
        desireRepository.save(desire);
    }

    @Override
    public List<Desire> findAllDesiresByUserId(Long userId) {
        return desireRepository.findAllByUserId(userId);
    }

}