package com.ggpsgeorge.spring_user_gaming_list;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
     * @return Game object in a ResponseEntity
     */
    @PostMapping("/add")
    public ResponseEntity<Game> addGame(@RequestBody Game game) {
        return ResponseEntity.ok().body(gameService.saveGame(game));
    }

    /**
     * Save a list of Game entities to the database
     * 
     * @param games
     * @return List of Game objects in a ResponseEntity
     */
    @PostMapping("/add-many")
    public ResponseEntity<List<Game>> addGames(@RequestBody List<Game> games) {
        return ResponseEntity.ok().body(gameService.saveGames(games));
    }

    /**
     * Find a game 
     * @param game_id
     * @return Game object in a ReponseEntity
     */
    @GetMapping("/{game_id}")
    public ResponseEntity<Game> getGame(@PathVariable Long game_id) {
        return ResponseEntity.ok().body(gameService.findGame(game_id));
    }

    /**
     * Find all saved games in the database
     * 
     * @return List of Game objects in a ReponseEntity
     */
    @GetMapping("/all")
    public ResponseEntity<List<Game>> getGames() {
        return ResponseEntity.ok().body(gameService.findAllGames());
    }
}
