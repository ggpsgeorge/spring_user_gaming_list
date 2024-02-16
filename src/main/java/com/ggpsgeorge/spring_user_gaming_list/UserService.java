package com.ggpsgeorge.spring_user_gaming_list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Class of the User
 * 
 * @author Fried Potato
 */

@Service
public class UserService {

    @Autowired UserRepository userRepository;

    /**
     * 
     * @param user user object to be persisted on the database
     * @return the userDTO object without the password field
     */
    public UserDTO saveUser(User user) {
        User persistedUser = userRepository.save(user);
        UserDTO returnUser = persistedUser.transformToDTO(persistedUser);
        return returnUser;
    }    

    /**
     * 
     * @param id id to find the the User
     * @return the userDTO object without the password field
     */
    public UserDTO safeFindUser(Long id) {
        User persistedUser = userRepository.findById(id).get();
        UserDTO returnUser = persistedUser.transformToDTO(persistedUser);
        return returnUser;
    }

    /**
     * 
     * @param id id to find the the User
     * @return the User object 
     */
    public User findUser(Long id) {
        return userRepository.findById(id).get();
    }

}
