package ua.com.desires.app.facade.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.desires.app.controller.dto.DesireDTO;
import ua.com.desires.app.converter.DesireMapper;
import ua.com.desires.app.exception.UserNotFoundException;
import ua.com.desires.app.facade.DesireFacade;
import ua.com.desires.app.model.Desire;
import ua.com.desires.app.model.User;
import ua.com.desires.app.service.DesireService;
import ua.com.desires.app.service.UserService;

import java.util.Optional;

@Slf4j
@Component
public class DefaultDesireFacade implements DesireFacade {

    private final DesireService desireService;
    private final UserService userService;
    private final DesireMapper desireMapper;

    @Autowired
    public DefaultDesireFacade(DesireService desireService, UserService userService, DesireMapper desireMapper) {
        this.desireService = desireService;
        this.userService = userService;
        this.desireMapper = desireMapper;
    }

    @Override
    public void saveDesire(DesireDTO desireDTO) {
        long userId = desireDTO.getUserId();
        Optional<User> optionalUser = userService.findUserById(userId);
        if (optionalUser.isEmpty()) {
            log.error("Error. Could not find user by ID: {}", userId);
            throw new UserNotFoundException();
        }
        Desire desire = desireMapper.toDesire(desireDTO);
        desire.setUser(optionalUser.get());
        desireService.saveDesire(desire, desireDTO.getImage());
    }

}