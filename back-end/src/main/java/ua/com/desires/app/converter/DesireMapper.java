package ua.com.desires.app.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.com.desires.app.controller.dto.DesireDTO;
import ua.com.desires.app.model.Desire;

@Mapper(componentModel = "spring")
public interface DesireMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "siteURL", target = "siteURL")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "currencyName", target = "currency")
    @Mapping(source = "priorityName", target = "priority")
    Desire toDesire(DesireDTO desireDTO);

}