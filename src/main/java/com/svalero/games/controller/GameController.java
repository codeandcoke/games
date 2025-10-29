package com.svalero.games.controller;

import com.svalero.games.domain.Game;
import com.svalero.games.exception.ErrorResponse;
import com.svalero.games.exception.GameNotFoundException;
import com.svalero.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/games")
    public ResponseEntity<List<Game>> getAll(@RequestParam(value = "category", defaultValue = "") String category) {
        List<Game> games;
        if (!category.isEmpty()) {
            games = gameService.findByCategory(category);
        } else {
            games = gameService.findAll();
        }

        return ResponseEntity.ok(games);
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Game> get(@PathVariable long id) throws GameNotFoundException {
        Game game = gameService.findById(id);
        return ResponseEntity.ok(game);
    }

    @PostMapping("/games")
    public ResponseEntity<Game> addGame(@RequestBody Game game) {
        // TODO Añadir validación
        // TODO Comprobar que no exista ya un juego con el mismo nombre
        Game newGame = gameService.add(game);
        return new ResponseEntity<>(newGame, HttpStatus.CREATED);
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<Game> modifyGame(@PathVariable long id, @RequestBody Game game) throws GameNotFoundException {
        Game newGame = gameService.modify(id, game);
        return ResponseEntity.ok(newGame);
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable long id) throws GameNotFoundException {
        gameService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(GameNotFoundException gnfe) {
        ErrorResponse errorResponse = new ErrorResponse(404, "not-found", "The game does not exist");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
