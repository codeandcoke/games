package com.svalero.games.service;

import com.svalero.games.domain.Game;
import com.svalero.games.dto.GameDto;
import com.svalero.games.dto.GameOutDto;
import com.svalero.games.exception.GameNotFoundException;
import com.svalero.games.repository.GameRepository;
import com.svalero.games.util.DateUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<GameOutDto> findAll(String category) {
        List<Game> games;

        if (!category.isEmpty()) {
            games = gameRepository.findByCategoryOrderByNameDesc(category);
        } else {
            games = gameRepository.findAll();
        }

        return modelMapper.map(games, new TypeToken<List<GameOutDto>>() {}.getType());
    }

    public GameDto findById(long id) throws GameNotFoundException {
        Game game = gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);

        GameDto gameDto = modelMapper.map(game, GameDto.class);
        gameDto.setDaysToRelease(DateUtil.getDaysBetweenDates(LocalDate.now(), gameDto.getReleaseDate()));
        return gameDto;
    }

    public Game modify(long id, Game game) throws GameNotFoundException {
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);

        modelMapper.map(game, existingGame);
        existingGame.setId(id);

        return gameRepository.save(existingGame);
    }
}
