package com.ggpsgeorge.spring_user_gaming_list_with_asserts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/users")
public class UserController {
    
    @Autowired UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody User user) {
        User persistedUser = userService.saveUser(user);
        UserDTO returnUser = persistedUser.transformToDTO(persistedUser);
        return ResponseEntity.ok().body(returnUser);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long user_id) {
        User persistedUser = userService.findUser(user_id);
        UserDTO returnUser = persistedUser.transformToDTO(persistedUser);
        return ResponseEntity.ok().body(returnUser);
    }

}
