package com.ggpsgeorge.spring_user_gaming_list_with_asserts;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "game_id")
    Long id;

    @Column(name = "game_name")
    String name;

    @Column(name = "game_genres")
    List<String> genres;

    @JsonIgnore
    @ManyToMany(mappedBy = "games")
    List<User> users;
}
