package com.ggpsgeorge.spring_user_gaming_list_with_asserts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    
}
