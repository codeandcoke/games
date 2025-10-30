package com.svalero.games.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotNull(message="name is mandatory")
    private String name;
    @Column
    private String description;
    @Column
    private String type;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Column
    @Min(value=0, message="the price must be a positive number")
    private float price;
    @Column
    private String category;

    @OneToMany(mappedBy = "game")
    @JsonBackReference
    private List<Review> reviews;
}
