package ua.com.desires.app.controller.dto;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class DesireDTO {

    Long id;
    @NotNull(message = "Name is required")
    String name;
    @Size(max = 100, message = "Description must be less than or equal to 100 characters")
    String description;
    String siteURL;
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @DecimalMax(value = "100000000", message = "Price must be less than 100 million")
    BigDecimal price;
    @NotNull(message = "Currency is required")
    @Pattern(regexp = "^(USD|EUR|UAH)$", message = "Currency must be one of USD, EUR, UAH")
    String currencyName;
    @NotNull(message = "Priority is required")
    @Pattern(regexp = "^(HIGH|MEDIUM|LOW)$", message = "Priority must be one of HIGH, MEDIUM, LOW")
    String priorityName;
    MultipartFile image;
    @NotNull(message = "User ID is required")
    Long userId;

}