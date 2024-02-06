package com.ggpsgeorge.spring_user_gaming_list_with_asserts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }    

    public User findUser(Long id) {
        return userRepository.findById(id).get();
    }

}
