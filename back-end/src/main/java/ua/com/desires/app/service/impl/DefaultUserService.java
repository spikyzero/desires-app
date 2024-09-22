package ua.com.desires.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.desires.app.exception.UserNotFoundException;
import ua.com.desires.app.model.User;
import ua.com.desires.app.repository.UserRepository;
import ua.com.desires.app.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DefaultUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {
        Optional<User> userToUpdate = userRepository.findById(id);
        if (userToUpdate.isEmpty()) {
            throw new UserNotFoundException();
        }
        User userToSave = userToUpdate.get();
        userToSave.setEmail(user.getEmail());
        userToSave.setName(user.getName());
        return userRepository.save(userToSave);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }

    @Override
    public boolean existUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}