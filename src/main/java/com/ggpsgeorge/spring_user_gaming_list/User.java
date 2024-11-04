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
import lombok.Builder;
import java.util.Objects;

/**
 * User Model
 * 
 * @author Fried Potato
 * 
 */

@Builder
@Entity
@Table(name = "user_")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="user_id")
    private Long id;

    @Column(nullable = false)
    private String userName;

    /**
     * DTO is necessary to not return the email string
     */
    @Column(nullable = false)
    private String email;

    /**
     * User password. Is transformed to a hash inside the database,
     * a DTO is necessary to not return the password string
     */
    @Column(nullable = false)
    private String password;
    
    /**
     * List of all favorite games of the user.
     * A ManyToMany relation is used
     */
    @ManyToMany
    @JoinTable(
        name = "user_games",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private List<Game> games;


    public User() {
    }

    public User(Long id, String userName, String email, String password, List<Game> games) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.games = games;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Game> getGames() {
        return this.games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public User id(Long id) {
        setId(id);
        return this;
    }

    public User userName(String userName) {
        setUserName(userName);
        return this;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User games(List<Game> games) {
        setGames(games);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(userName, user.userName) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(games, user.games);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, email, password, games);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", userName='" + getUserName() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", games='" + getGames() + "'" +
            "}";
    }
    
    
}