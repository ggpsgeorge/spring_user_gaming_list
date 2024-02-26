package com.ggpsgeorge.spring_user_gaming_list;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class GameRepositoryTests {

    @Autowired GameRepository gameRepository;

    Game game1 = Game.builder()
    .id(1L)
    .name("Super Mario 64")
    .genres(Arrays.asList("Platform", "Kids"))
    .build();

    Game game2 = Game.builder()
    .id(2L)
    .name("Elden Ring")
    .genres(Arrays.asList("Fantasy", "Challenging"))
    .build();
    
    Game game3 = Game.builder()
    .id(666L)
    .name("Doom (2016)")
    .genres(Arrays.asList("FPS", "Gore"))
    .build();

    @Test
    public void testSave_shouldReturnGame() {
        Game savedGame = gameRepository.save(game1);

        Assertions.assertThat(savedGame.getId()).isGreaterThan(0);
        Assertions.assertThat(savedGame.getName()).isEqualTo("Super Mario 64");
        Assertions.assertThat(savedGame.getGenres()).isEqualTo(Arrays.asList("Platform", "Kids"));
    }

    @Test
    public void testSaveAll_shouldReturnGameList() {
        List<Game> games = Arrays.asList(game1, game2, game3);

        List<Game> savedGames = gameRepository.saveAll(games);

        Assertions.assertThat(savedGames.get(0)).isEqualTo(game1);
        Assertions.assertThat(savedGames.get(1).getGenres()).isEqualTo(Arrays.asList("Fantasy", "Challenging"));
        Assertions.assertThat(savedGames.get(2).getName()).isEqualTo("Doom (2016)");
    }
    
}
