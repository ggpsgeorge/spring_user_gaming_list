package com.ggpsgeorge.spring_user_gaming_list;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

/**
 * User Controller Class
 * 
 * @author Fried Potato
 */

@Controller
@RequestMapping("/api/v1/users")
public class UserController {
    
    @Autowired UserService userService;
    @Autowired GameService gameService;
    @Autowired PasswordEncoder passwordEncoder;
    @Autowired ModelMapper modelMapper;

    /**
     * Save the User entity to the database
     * 
     * @param user User object to be added to the database
     * @return ResponseEntity with a UserDTO object and 201 Created status
     */
    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody @Valid User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(transformToDTO(userService.saveUser(user)));
    }

    /**
     * Find a User with a id
     * 
     * @param user_id 
     * @return ReponseEntity with UserDTO object
     */
    @GetMapping("/{user_id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long user_id) {
        return ResponseEntity.ok().body(transformToDTO(userService.findUser(user_id)));
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

        List<Game> games = persistedUser.getGames();
        games.add(persistedGame);
        persistedUser.setGames(games);
        
        userService.saveUser(persistedUser);
        
        return ResponseEntity.ok().body(transformToDTO(persistedUser));
    }

    /**
     * Delete a User
     * 
     * @param user_id
     * @return String
     */
    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long user_id) {
        userService.removeUser(user_id);
        return ResponseEntity.ok().body("User " + user_id + " was removed"); 
    }

    /**
     * Update User model, except the user_id field
     * 
     * @param user
     * @param user_id
     * @return UserDTO
     */
    @PutMapping("/update/{user_id}")
    public ResponseEntity<UserDTO> putUser(
        @RequestBody User user, @PathVariable Long user_id) {

        User persistedUser = userService.findUser(user_id);
        
        persistedUser.setUserName(user.getUserName());
        persistedUser.setEmail(user.getEmail());
        persistedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getGames() != null) {
            persistedUser.setGames(user.getGames());
        }

        return ResponseEntity.accepted().body(transformToDTO(userService.saveUser(persistedUser)));
    }

    /**
     * Map a User model to a UserDTO model
     * @param model
     * @return UserDTO
     */
    public UserDTO transformToDTO(User model){
        return modelMapper.map(model, UserDTO.class);
    }

}
