package com.ggpsgeorge.spring_user_gaming_list;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    @SuppressWarnings("unchecked")
    public Game save(Game game);
    public Optional<Game> findById(Long id);
    public List<Game> findAll();
}
