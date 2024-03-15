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
        return userRepository.save(user);
    }    

    /**
     * 
     * @param id id to find a User
     * @return User object, if not exists then null
     */
    public User findUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}
