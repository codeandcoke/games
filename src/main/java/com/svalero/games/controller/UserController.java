package com.svalero.games.controller;

import com.svalero.games.domain.Game;
import com.svalero.games.domain.User;
import com.svalero.games.exception.ErrorResponse;
import com.svalero.games.exception.UserNotFoundException;
import com.svalero.games.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        return null;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> get(@PathVariable long id) throws UserNotFoundException {
        return null;
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        // TODO Añadir validación
        // TODO Comprobar que no exista ya un usuario con el mismo username
        return null;
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> modifyUser(@PathVariable long id, @RequestBody User user) throws UserNotFoundException {
        return null;
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) throws UserNotFoundException {
        return null;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(UserNotFoundException unfe) {
        ErrorResponse errorResponse = new ErrorResponse(404, "not-found", "The user does not exist");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
