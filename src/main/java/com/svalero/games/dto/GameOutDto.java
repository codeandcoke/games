package com.svalero.games.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameOutDto {
    private long id;
    private String name;
    private String description;
    private String type;
    private String category;
}
