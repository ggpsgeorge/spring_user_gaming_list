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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Game Model
 * 
 * @author Fried Potato
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "game_id")
    private Long id;

    @Column(name = "game_name", nullable = false)
    private String name;

    @Column(name = "game_genres")
    private List<String> genres;

    @JsonIgnore
    @ManyToMany(mappedBy = "games")
    private List<User> users;
}
