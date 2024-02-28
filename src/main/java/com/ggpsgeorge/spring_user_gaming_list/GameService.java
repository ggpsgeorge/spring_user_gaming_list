package com.ggpsgeorge.spring_user_gaming_list;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Class of the Game Model
 */

@Service
public class GameService {

    @Autowired GameRepository gameRepository;


    /**
     * 
     * @param game game object to be saved in the database
     * @return the game object saved
     */
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    /**
     * 
     * @param games list of game object. Each game object is saved in the database
     * @return the list of game objects
     */
    public List<Game> saveGames(List<Game> games) {
        return gameRepository.saveAll(games);
    }

    /**
     * 
     * @param id id to find a game
     * @return Game object, if not exists then null
     */
    public Game findGame(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    /**
     * 
     * @return return a list of all games in the database
     */
    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }
}
