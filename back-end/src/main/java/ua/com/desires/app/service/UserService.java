package ua.com.desires.app.service;

import ua.com.desires.app.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmail(String email);

    List<User> findAllUsers();

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    boolean existUserByEmail(String email);

}