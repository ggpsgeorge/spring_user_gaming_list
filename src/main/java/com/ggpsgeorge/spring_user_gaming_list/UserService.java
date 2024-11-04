package com.ggpsgeorge.spring_user_gaming_list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service Class of the User Model
 * 
 * @author Fried Potato
 */

@Service
public class UserService {

    @Autowired UserRepository userRepository;
    @Autowired PasswordEncoder passwordEncoder;

    /**
     * 
     * @param user User object to be saved in the database
     * @return User object
     */
    public User saveUser(User user) {
        User savedUser = userRepository.save(user);
        
        User returnUser = User.builder()
        .id(savedUser.getId())
        .userName(savedUser.getUserName())
        .email(savedUser.getEmail())
        .password(savedUser.getPassword())
        .games(savedUser.getGames())
        .build();

        return returnUser;
    }    

    /**
     * 
     * @param id id to find a User
     * @return User object, if not exists throws an error
     */
    public User findUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User updateUser(Long id, User user) {
        User persistedUser = userRepository.findById(id).orElseThrow();
        
        persistedUser.setUserName(user.getUserName());
        persistedUser.setEmail(user.getEmail());
        persistedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getGames() != null) {
            persistedUser.setGames(user.getGames());
        }

        return userRepository.save(persistedUser);

    }

    /**
     * 
     * @param id
     */
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

}
