package ua.com.desires.app.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.com.desires.app.controller.form.UserRegistrationForm;
import ua.com.desires.app.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "email", target = "email")
    @Mapping(source = "name", target = "name")
    User toUser(UserRegistrationForm userForm);

}