package com.ggpsgeorge.spring_user_gaming_list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Class of the User Model
 * 
 * @author Fried Potato
 */

@Service
public class UserService {

    @Autowired UserRepository userRepository;

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
        return userRepository.findById(id).orElse(null);
    }

    /**
     * 
     * @param id
     */
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

}
