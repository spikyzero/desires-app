package ua.com.desires.app.service;

import org.springframework.web.multipart.MultipartFile;
import ua.com.desires.app.model.Desire;

import java.util.List;

public interface DesireService {

    void saveDesire(Desire desire, MultipartFile image);

    List<Desire> findAllDesiresByUserId(Long userId);

}