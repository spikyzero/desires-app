package ua.com.desires.app.controller.form.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationForm {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    String email;
    @NotBlank(message = "Name is required")
    String name;
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    String password;

}