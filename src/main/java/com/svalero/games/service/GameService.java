package com.svalero.games.service;

import com.svalero.games.domain.Game;
import com.svalero.games.exception.GameNotFoundException;
import com.svalero.games.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game add(Game game) {
        return gameRepository.save(game);
    }

    public void delete(long id) throws GameNotFoundException {
        Game game = gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);

        gameRepository.delete(game);
    }

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public List<Game> findByCategory(String category) {
        return gameRepository.findByCategory(category);
    }

    public Game findById(long id) throws GameNotFoundException {
        return gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);
    }

    public Game modify(long id, Game game) throws GameNotFoundException {
        Game oldGame = gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);

        // TODO Más adelante usaremos ModelMapper para mapear atributos entre objetos
        oldGame.setName(game.getName());
        oldGame.setType(game.getType());
        oldGame.setPrice(game.getPrice());
        oldGame.setDescription(game.getDescription());
        oldGame.setReleaseDate(game.getReleaseDate());
        oldGame.setCategory(game.getCategory());

        return gameRepository.save(oldGame);
    }
}
