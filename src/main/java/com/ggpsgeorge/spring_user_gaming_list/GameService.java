package com.ggpsgeorge.spring_user_gaming_list;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired GameRepository gameRepository;

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public List<Game> saveGames(List<Game> games) {
        return gameRepository.saveAll(games);
    }

    public Game findGame(Long id) {
        return gameRepository.findById(id).get();
    }

    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }
}
