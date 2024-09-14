package ua.com.desires.app.facade.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ua.com.desires.app.controller.dto.UserDTO;
import ua.com.desires.app.controller.form.UserRegistrationForm;
import ua.com.desires.app.controller.form.UserUpdateForm;
import ua.com.desires.app.converter.UserMapper;
import ua.com.desires.app.exception.NotUniqueEmailException;
import ua.com.desires.app.exception.UserNotFoundException;
import ua.com.desires.app.facade.UserFacade;
import ua.com.desires.app.model.User;
import ua.com.desires.app.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DefaultUserFacade implements UserFacade {

    private static final String ROLE_DEFAULT = "USER";
    private final UserService userService;
    private final UserMapper userMapper;

    public DefaultUserFacade(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO saveUser(UserRegistrationForm userForm) {
        if (userService.existUserByEmail(userForm.getEmail())) {
            log.error("Error. Not unique email address: {}", userForm.getEmail());
            throw new NotUniqueEmailException();
        }
        User user = userMapper.toUser(userForm);
        user.setRole(ROLE_DEFAULT);
        return userMapper.toUserDTO(userService.saveUser(user));
    }

    @Override
    public UserDTO findUserById(Long id) {
        return userService.findUserById(id)
                .map(userMapper::toUserDTO)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userService.findAllUsers().stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserUpdateForm userForm) {
        User updatedUser = userService.updateUser(id, userMapper.toUser(userForm));
        return userMapper.toUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

    @Override
    public boolean existUserByEmail(String email) {
        return userService.existUserByEmail(email);
    }

}