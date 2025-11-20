package com.svalero.games.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class ReviewInDto {

    private LocalDate playDate;
    @Min(value = 1, message = "rate must be between 1 and 5")
    @Max(value = 5, message = "rate must be between 1 and 5")
    private int rate;
    @NotEmpty(message = "description is mandatory")
    private String description;
    private boolean recommendation;
    private long userId;
}
