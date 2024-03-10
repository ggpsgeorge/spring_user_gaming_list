package com.ggpsgeorge.spring_user_gaming_list;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

/**
 * Game Controller Class
 * 
 * @author Fried Potato
 */

@Controller
@RequestMapping("api/v1/games")
public class GameController {
    
    @Autowired GameService gameService;

    /**
     * Save the Game entity to the database
     * 
     * @param game
     * @return ResponseEntity with a game object
     */
    @PostMapping("/add")
    public ResponseEntity<Game> addGame(@RequestBody @Valid Game game) {
        Game savedGame = gameService.saveGame(game);

        URI uri = URI.create("/api/v1/games/" + savedGame.getId());

        return ResponseEntity.created(uri).body(savedGame);
    }

    /**
     * Save a list of Game entities to the database
     * 
     * @param games
     * @return ResponseEntity with a list of game objects
     */
    @PostMapping("/add-many")
    public ResponseEntity<List<Game>> addGames(@RequestBody @Valid List<Game> games) {
        return ResponseEntity.ok().body(gameService.saveGames(games));
    }

    /**
     * Find a game 
     * @param game_id
     * @return ReponseEntity with a game object
     */
    @GetMapping("/{game_id}")
    public ResponseEntity<Game> getGame(@PathVariable Long game_id) {
        return ResponseEntity.ok().body(gameService.findGame(game_id));
    }

    /**
     * Find all saved games in the database
     * 
     * @return ReponseEntity with a list of game objects
     */
    @GetMapping("/all")
    public ResponseEntity<List<Game>> getGames() {
        return ResponseEntity.ok().body(gameService.findAllGames());
    }
}
