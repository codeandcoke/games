package com.svalero.games.service;

import com.svalero.games.domain.Game;
import com.svalero.games.domain.Review;
import com.svalero.games.domain.User;
import com.svalero.games.dto.ReviewInDto;
import com.svalero.games.exception.ReviewNotFoundException;
import com.svalero.games.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review add(ReviewInDto reviewInDto, Game game, User user) {
        // TODO Usar ModelMapper
        Review review = new Review();
        review.setGame(game);
        // FIXME Añadir operación para registrar usuario
//        review.setUser(user);
        review.setDescription(reviewInDto.getDescription());
        review.setRate(reviewInDto.getRate());
        review.setPlayDate(reviewInDto.getPlayDate());

        return reviewRepository.save(review);
    }

    public void delete(long id) throws ReviewNotFoundException {

    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review findById(long id) throws ReviewNotFoundException {
        return null;
    }

    public Review modify(long id, Review review) throws ReviewNotFoundException {
        return null;
    }
}
