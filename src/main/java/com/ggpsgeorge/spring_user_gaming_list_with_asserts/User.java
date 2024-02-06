package com.ggpsgeorge.spring_user_gaming_list_with_asserts;

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
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * One of the main entities.
 * 
 * @author Fried Potato
 * 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    Long id;

    @Column(name = "user_username")
    String userName;

    /**
     * DTO is necessary to not return the email string
     */
    @Column(name = "user_email")
    String email;

    /**
     * User password. Is transformed to a hash inside the database,
     * a DTO is necessary to not return the password string
     */
    @Column(name = "user_password")
    String password;
    
    /**
     * List of all favorite games of the user.
     * A ManyToMany relation is used.
     */
    @ManyToMany
    @JoinTable(
        name = "user_games",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    List<Game> games;

    /**
     * <p>
     * Function transform a User to a UserDTO
     * </p>
     * @param user the user object that will be transformed to userDTO
     * @return the userDTO object
     */
    public UserDTO transformToDTO(User user) {
        UserDTO newUser = new UserDTO();

        newUser.setId(user.id);
        newUser.setUserName(user.userName);
        newUser.setGames(user.games);

        return newUser;
    }
    
}