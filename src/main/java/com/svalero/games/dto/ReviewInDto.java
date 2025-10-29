package com.svalero.games.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewInDto {
    private LocalDate playDate;
    private int rate;
    private String description;
    private boolean recommendation;
    private long userId;
    private long gameId;
}
