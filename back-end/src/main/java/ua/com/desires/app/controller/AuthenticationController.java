package ua.com.desires.app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import ua.com.desires.app.controller.form.UserLoginForm;
import ua.com.desires.app.controller.response.ApiResponse;
import ua.com.desires.app.controller.response.JwtResponse;
import ua.com.desires.app.webtoken.CustomUserDetailsService;
import ua.com.desires.app.webtoken.JwtService;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping("/home")
    public String handleWelcome() {
        return "Welcome to home!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/home")
    public String handleAdminHome() {
        return "Welcome to ADMIN home!";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/home")
    public String handleUserHome() {
        return "Welcome to USER home!";
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateAndGetToken(@Valid @RequestBody UserLoginForm loginForm) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));
            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(customUserDetailsService.loadUserByUsername(loginForm.getEmail()));
                return ResponseEntity.ok(new JwtResponse(token));
            }
            return new ResponseEntity<>(new ApiResponse("Not authenticated"), HttpStatus.UNAUTHORIZED);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(new ApiResponse("Authentication failed"), HttpStatus.UNAUTHORIZED);
        }
    }

}