package ua.com.desires.app.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.com.desires.app.controller.dto.UserDTO;
import ua.com.desires.app.controller.form.UserRegistrationForm;
import ua.com.desires.app.controller.form.UserUpdateForm;
import ua.com.desires.app.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "email", target = "email")
    @Mapping(source = "name", target = "name")
    User toUser(UserRegistrationForm userForm);

    @Mapping(source = "email", target = "email")
    @Mapping(source = "name", target = "name")
    User toUser(UserUpdateForm userForm);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "role", target = "role")
    UserDTO toUserDTO(User user);

}