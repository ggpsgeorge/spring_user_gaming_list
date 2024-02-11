package com.ggpsgeorge.spring_user_gaming_list_with_asserts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User Controller Class
 * 
 * @author Fried Potato
 */

@Controller
@RequestMapping("api/v1/users")
public class UserController {
    
    @Autowired UserService userService;
    @Autowired GameService gameService;

    /**
     * Save the User entity to the database
     * 
     * @param user User object to be added to the database
     * @return UserDTO in a ResponseEntity
     */
    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

    /**
     * Find a User with a id
     * 
     * @param user_id 
     * @return UserDTO in a ResponseEntity
     */
    @GetMapping("/{user_id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long user_id) {
        return ResponseEntity.ok().body(userService.safeFindUser(user_id));
    }

    /**
     * Register a Game to a User
     * 
     * @param user_id
     * @param game_id
     * @return UserDTO in a ResponseEntity
     */
    @PutMapping("/{user_id}/register/{game_id}")
    public ResponseEntity<UserDTO> registerGameToList(
        @PathVariable Long user_id, @PathVariable Long game_id ) {
        
        User persistedUser = userService.findUser(user_id);
        Game persistedGame = gameService.findGame(game_id);

        persistedUser.games.add(persistedGame);
        userService.saveUser(persistedUser);
        
        return ResponseEntity.ok().body(userService.safeFindUser(user_id));
    }

}
