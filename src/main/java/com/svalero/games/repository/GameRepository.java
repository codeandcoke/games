package com.svalero.games.repository;

import com.svalero.games.domain.Game;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

    List<Game> findAll();
    List<Game> findByCategoryOrderByNameDesc(String category);
    List<Game> findByCategoryAndTypeOrderByNameDesc(String category, String type);

    // JPQL
    @Query(
            value = "SELECT g FROM Game g WHERE g IN (SELECT r.game FROM Review r) AND g.category = :category")
    List<Game> findGamesWithReviews(String category);

    // SQL Nativo
    @NativeQuery(
            value = "SELECT * FROM games g WHERE g.id IN (SELECT r.game_id FROM reviews r) AND g.category = :category")
    List<Game> findGameWithReviewsNative(String category);
}
