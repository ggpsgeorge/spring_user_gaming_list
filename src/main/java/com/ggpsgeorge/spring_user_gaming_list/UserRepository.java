package com.ggpsgeorge.spring_user_gaming_list;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository Class of the User
 * 
 * @author Fried Potato
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
  
} 