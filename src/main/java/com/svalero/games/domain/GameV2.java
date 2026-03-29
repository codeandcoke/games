package com.svalero.games.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "GameV2")
@Table(name = "games")
public class GameV2 {
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    @Column
    @Min(value=0, message="the price must be a positive number")
    private float price;
    @Column
    private String category;
    @Column
    private String comments;
    @Column
    @NotNull(message="rate is mandatory")
    private int rate;

    @OneToMany(mappedBy = "game")
    @JsonBackReference
    private List<Review> reviews;
}
