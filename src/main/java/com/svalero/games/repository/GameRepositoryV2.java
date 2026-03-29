package com.svalero.games.repository;

import com.svalero.games.domain.GameV2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepositoryV2 extends CrudRepository<GameV2, Long> {
}
