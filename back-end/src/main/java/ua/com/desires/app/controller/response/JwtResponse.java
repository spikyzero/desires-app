package ua.com.desires.app.controller.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ua.com.desires.app.controller.dto.UserDTO;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class JwtResponse {

    String token;
    UserDTO userDTO;

}