package ua.com.desires.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.desires.app.controller.response.ApiResponse;


@RestController
@RequestMapping("/api/v1/roles")
public class RolesController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<?> checkAdmin() {
        return new ResponseEntity<>(new ApiResponse("Valid admin credentials"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<?> checkUser() {
        return new ResponseEntity<>(new ApiResponse("Valid user credentials"), HttpStatus.OK);
    }

}