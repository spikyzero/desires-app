package ua.com.desires.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.com.desires.app.model.User;
import ua.com.desires.app.service.UserService;

@Configuration
public class DataInitializer {

    @Value("${admin.default.email}")
    private String adminEmail;
    @Value("${admin.default.name}")
    private String adminName;
    @Value("${admin.default.password}")
    private String adminPassword;
    @Value("${admin.default.role}")
    private String adminRole;

    @Bean
    public CommandLineRunner init(UserService userService) {
        return args -> {
            if (!userService.existUserByEmail(adminEmail)) {
                User admin = new User();
                admin.setEmail(adminEmail);
                admin.setName(adminName);
                admin.setPassword(adminPassword);
                admin.setRole(adminRole);
                userService.saveUser(admin);
            }
        };
    }

}