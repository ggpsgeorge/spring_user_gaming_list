package com.ggpsgeorge.spring_user_gaming_list_with_asserts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/games")
public class GameController {
    
    @Autowired GameService gameService;

    @PostMapping("/add")
    public ResponseEntity<Game> addGame(@RequestBody Game game) {
        return ResponseEntity.ok().body(gameService.saveGame(game));
    }

    @PostMapping("/add-many")
    public ResponseEntity<List<Game>> addGames(@RequestBody List<Game> games) {
        return ResponseEntity.ok().body(gameService.saveGames(games));
    }

    @GetMapping("/{game_id}")
    public ResponseEntity<Game> getGame(@PathVariable Long game_id) {
        return ResponseEntity.ok().body(gameService.findGame(game_id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Game>> getGames() {
        return ResponseEntity.ok().body(gameService.findAllGames());
    }
}
