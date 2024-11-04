package com.ggpsgeorge.spring_user_gaming_list;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import java.util.Objects;

/**
 * Game Model
 * 
 * @author Fried Potato
 */

@Builder
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="game_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private List<String> genres;

    @JsonIgnore
    @ManyToMany(mappedBy = "games")
    private List<User> users;


    public Game() {
    }

    public Game(Long id, String name, List<String> genres, List<User> users) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.users = users;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGenres() {
        return this.genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Game id(Long id) {
        setId(id);
        return this;
    }

    public Game name(String name) {
        setName(name);
        return this;
    }

    public Game genres(List<String> genres) {
        setGenres(genres);
        return this;
    }

    public Game users(List<User> users) {
        setUsers(users);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Game)) {
            return false;
        }
        Game game = (Game) o;
        return Objects.equals(id, game.id) && Objects.equals(name, game.name) && Objects.equals(genres, game.genres) && Objects.equals(users, game.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, genres, users);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", genres='" + getGenres() + "'" +
            ", users='" + getUsers() + "'" +
            "}";
    }
    
}
