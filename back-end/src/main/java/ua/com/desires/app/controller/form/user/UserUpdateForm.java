package ua.com.desires.app.controller.form.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateForm {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    String email;
    @NotBlank(message = "Name is required")
    String name;

}