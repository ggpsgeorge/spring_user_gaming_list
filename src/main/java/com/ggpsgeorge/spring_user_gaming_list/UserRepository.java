package com.ggpsgeorge.spring_user_gaming_list;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


/**
 * Repository Class of the User
 * 
 * @author Fried Potato
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @SuppressWarnings("unchecked")
    public User save(User user);
    public Optional<User> findById(Long id);
} 