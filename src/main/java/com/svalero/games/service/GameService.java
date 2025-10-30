package com.svalero.games.service;

import com.svalero.games.domain.Game;
import com.svalero.games.exception.GameNotFoundException;
import com.svalero.games.repository.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private ModelMapper modelMapper;

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
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);

        modelMapper.map(game, existingGame);
        existingGame.setId(id);

        return gameRepository.save(existingGame);
    }
}
