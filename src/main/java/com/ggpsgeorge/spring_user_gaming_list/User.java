package com.ggpsgeorge.spring_user_gaming_list;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User Model
 * 
 * @author Fried Potato
 * 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    Long id;

    @Column(name = "user_username", nullable = false)
    String userName;

    /**
     * DTO is necessary to not return the email string
     */
    @Column(name = "user_email", nullable = false)
    String email;

    /**
     * User password. Is transformed to a hash inside the database,
     * a DTO is necessary to not return the password string
     */
    @Column(name = "user_password", nullable = false)
    String password;
    
    /**
     * List of all favorite games of the user.
     * A ManyToMany relation is used
     */
    @ManyToMany
    @JoinTable(
        name = "user_games",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    List<Game> games;

    /**
     * Function transform a User object to a UserDTO object. The passwords
     * and email fields are removed
     * 
     * @param user the user object that will be transformed to userDTO
     * @return the userDTO object
     */
    public UserDTO transformToDTO(User user) {
        
        UserDTO newUser = UserDTO.builder()
        .id(user.getId())
        .userName(user.getUserName())
        .games(user.getGames())
        .build();

        return newUser;
    }
    
}