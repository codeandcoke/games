package com.svalero.games.controller;

import com.svalero.games.domain.Game;
import com.svalero.games.domain.Review;
import com.svalero.games.domain.User;
import com.svalero.games.dto.ReviewInDto;
import com.svalero.games.exception.ErrorResponse;
import com.svalero.games.exception.GameNotFoundException;
import com.svalero.games.exception.ReviewNotFoundException;
import com.svalero.games.exception.UserNotFoundException;
import com.svalero.games.service.GameService;
import com.svalero.games.service.ReviewService;
import com.svalero.games.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAll() {
        List<Review> reviews = reviewService.findAll();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> get(@PathVariable long id) throws ReviewNotFoundException {
        return null;
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> addReview(@RequestBody ReviewInDto reviewInDto)
            throws GameNotFoundException, UserNotFoundException{
        // TODO Añadir validación
        Game game = gameService.findById(reviewInDto.getGameId());
        User user = userService.findById(reviewInDto.getUserId());
        Review review = reviewService.add(reviewInDto, game, user);
        // TODO Devolver un objeto ReviewOutDto
        return ResponseEntity.ok(review);
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<Review> modifyReview(@PathVariable long id, @RequestBody Review review) throws ReviewNotFoundException {
        return null;
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable long id) throws ReviewNotFoundException {
        return null;
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(ReviewNotFoundException rnfe) {
        ErrorResponse errorResponse = ErrorResponse.notFound("The review does not exist");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(UserNotFoundException unfe) {
        ErrorResponse errorResponse = ErrorResponse.notFound("The user does not exist");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
