package com.svalero.games.domain;

import jakarta.persistence.*;
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
    private String name;
    @Column
    private String description;
    @Column
    private String type;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Column
    private float price;
    @Column
    private String category;

    @OneToMany(mappedBy = "game")
    private List<Review> reviews;
}
