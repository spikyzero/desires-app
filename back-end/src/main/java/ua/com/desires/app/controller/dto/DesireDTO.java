package ua.com.desires.app.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class DesireDTO {

    Long id;
    String name;
    String description;
    String siteURL;
    BigDecimal price;
    String currencyName;
    String priorityName;
    String imageBase64;
    Long userId;

}