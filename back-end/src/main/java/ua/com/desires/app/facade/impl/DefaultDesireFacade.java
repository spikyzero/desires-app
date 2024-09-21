package ua.com.desires.app.facade.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.desires.app.controller.dto.DesireDTO;
import ua.com.desires.app.controller.form.desire.DesireForm;
import ua.com.desires.app.converter.DesireMapper;
import ua.com.desires.app.exception.UserNotFoundException;
import ua.com.desires.app.facade.DesireFacade;
import ua.com.desires.app.model.Desire;
import ua.com.desires.app.model.User;
import ua.com.desires.app.service.DesireService;
import ua.com.desires.app.service.FileService;
import ua.com.desires.app.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DefaultDesireFacade implements DesireFacade {

    private final DesireMapper desireMapper;
    private final DesireService desireService;
    private final UserService userService;
    private final FileService fileService;

    @Autowired
    public DefaultDesireFacade(
            DesireMapper desireMapper,
            DesireService desireService,
            UserService userService,
            FileService fileService) {
        this.desireMapper = desireMapper;
        this.desireService = desireService;
        this.userService = userService;
        this.fileService = fileService;
    }

    @Override
    public void saveDesire(DesireForm desireForm) {
        long userId = desireForm.getUserId();
        Optional<User> optionalUser = userService.findUserById(userId);
        if (optionalUser.isEmpty()) {
            log.error("Error. Could not find user by ID: {}", userId);
            throw new UserNotFoundException();
        }
        Desire desire = desireMapper.toDesire(desireForm);
        desire.setUser(optionalUser.get());
        desireService.saveDesire(desire, desireForm.getImage());
    }

    @Override
    public List<DesireDTO> findAllDesires(long userId) {
        List<Desire> desires = desireService.findAllDesiresByUserId(userId);
        return desires.stream()
                .map(d -> convertToDTO(d, d.getImageURL(), userId))
                .collect(Collectors.toList());
    }

    private DesireDTO convertToDTO(Desire desire, String imageURL, long userId) {
        DesireDTO desireDTO = desireMapper.toDesireDTO(desire);
        if (StringUtils.isNotEmpty(imageURL)) {
            String imageBase64 = fileService.getImageBase64(desire.getImageURL());
            desireDTO.setImageBase64(imageBase64);
        }
        desireDTO.setUserId(userId);
        return desireDTO;
    }

}