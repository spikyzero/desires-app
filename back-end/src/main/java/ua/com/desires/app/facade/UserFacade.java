package ua.com.desires.app.facade;

import ua.com.desires.app.controller.dto.UserDTO;
import ua.com.desires.app.controller.form.user.UserRegistrationForm;
import ua.com.desires.app.controller.form.user.UserUpdateForm;

import java.util.List;

public interface UserFacade {

    UserDTO saveUser(UserRegistrationForm userForm);

    UserDTO findUserById(Long id);

    UserDTO findUserByEmail(String email);

    List<UserDTO> findAllUsers();

    UserDTO updateUser(Long id, UserUpdateForm userForm);

    void deleteUser(Long id);

    boolean existUserByEmail(String email);

}