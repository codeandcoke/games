package com.svalero.games.service;

import com.svalero.games.domain.Game;
import com.svalero.games.domain.Review;
import com.svalero.games.domain.User;
import com.svalero.games.dto.GameDto;
import com.svalero.games.dto.ReviewInDto;
import com.svalero.games.exception.ReviewNotFoundException;
import com.svalero.games.repository.GameRepository;
import com.svalero.games.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Review add(ReviewInDto reviewInDto, GameDto gameDto, User user) {
        Review review = new Review();
        // FIXME Mejorar
        review.setGame(gameRepository.findById(gameDto.getId()).get());
        review.setUser(user);
        modelMapper.map(reviewInDto, review);

        return reviewRepository.save(review);
    }

    public void delete(long id) throws ReviewNotFoundException {

    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public List<Review> findByGame(GameDto gameDto) {
        Game game = modelMapper.map(gameDto, Game.class);
        return reviewRepository.findByGame(game);
    }

    public Review findById(long id) throws ReviewNotFoundException {
        return null;
    }

    public Review modify(long id, Review review) throws ReviewNotFoundException {
        return null;
    }
}
