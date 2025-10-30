package com.svalero.games.controller;

import com.svalero.games.domain.Game;
import com.svalero.games.dto.GameOutDto;
import com.svalero.games.exception.ErrorResponse;
import com.svalero.games.exception.GameNotFoundException;
import com.svalero.games.service.GameService;
import jakarta.validation.Valid;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/games")
    public ResponseEntity<List<GameOutDto>> getAll(@RequestParam(value = "category", defaultValue = "") String category) {
        List<Game> games;
        if (!category.isEmpty()) {
            games = gameService.findByCategory(category);
        } else {
            games = gameService.findAll();
        }

        List<GameOutDto> gamesOutDto = modelMapper.map(games, new TypeToken<List<GameOutDto>>() {}.getType());
        return ResponseEntity.ok(gamesOutDto);
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Game> get(@PathVariable long id) throws GameNotFoundException {
        Game game = gameService.findById(id);
        return ResponseEntity.ok(game);
    }

    @PostMapping("/games")
    public ResponseEntity<Game> addGame(@Valid @RequestBody Game game) {
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
        ErrorResponse errorResponse = ErrorResponse.notFound("The game does not exist");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException manve) {
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ErrorResponse errorResponse = ErrorResponse.validationError(errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
