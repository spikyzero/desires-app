package ua.com.desires.app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.desires.app.controller.dto.UserDTO;
import ua.com.desires.app.controller.form.UserRegistrationForm;
import ua.com.desires.app.controller.form.UserUpdateForm;
import ua.com.desires.app.controller.response.ApiResponse;
import ua.com.desires.app.exception.NotUniqueEmailException;
import ua.com.desires.app.exception.UserNotFoundException;
import ua.com.desires.app.facade.UserFacade;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserFacade userFacade;

    @Autowired
    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationForm userForm) {
        try {
            return ResponseEntity.ok(userFacade.saveUser(userForm));
        } catch (NotUniqueEmailException e) {
            return new ResponseEntity<>(new ApiResponse(String.format("User with email '%s' already exists", userForm.getEmail())), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse("Registration failed"), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userFacade.findUserById(id));
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(String.format("Could not find user by ID: '%d'", id)), HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping
    public List<UserDTO> getUsers() {
        return userFacade.findAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateForm userForm) {
        try {
            return ResponseEntity.ok(userFacade.updateUser(id, userForm));
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(String.format("Could not update user by ID: '%d'", id)), HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userFacade.deleteUser(id);
            return new ResponseEntity<>(new ApiResponse(String.format("User deleted successfully by ID: '%d'", id)), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(String.format("Could not delete user by ID: '%d'", id)), HttpStatus.NOT_FOUND);
        }
    }

}