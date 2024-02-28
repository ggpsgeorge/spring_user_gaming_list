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
     * @return UserDTO object
     */
    public UserDTO saveUser(User user) {
        User persistedUser = userRepository.save(user);
        UserDTO returnUser = persistedUser.transformToDTO(persistedUser);
        return returnUser;
    }    

    /**
     * Will find the User and return the DTO. So, the 
     * password and email fields from User are removed
     * 
     * @param id id to find a User
     * @return UserDTO object, if not exists then null
     */
    public UserDTO safeFindUser(Long id) {
        User persistedUser = userRepository.findById(id).orElse(null);
        if (persistedUser == null) {
            return null;
        }
        
        UserDTO returnUser = persistedUser.transformToDTO(persistedUser);
        return returnUser;
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
