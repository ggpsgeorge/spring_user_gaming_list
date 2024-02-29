package com.ggpsgeorge.spring_user_gaming_list;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameServiceTests {

    @Mock 
    GameRepository gameRepository;

    @InjectMocks
    GameService gameService;

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

    List<Game> games = Arrays.asList(game1, game2, game3);

    @Test
    public void testSaveGame_shouldReturnGame() {
        Mockito.when(gameRepository.save(game2)).thenReturn(game2);

        Game savedGame = gameService.saveGame(game2);

        Assertions.assertThat(savedGame).isNotNull();
        Assertions.assertThat(savedGame.getId()).isEqualTo(2L);
        Assertions.assertThat(savedGame.getName()).isEqualTo("Elden Ring");
        Assertions.assertThat(savedGame.getGenres()).isEqualTo(Arrays.asList("Fantasy", "Challenging"));
    }

    @Test
    public void testSaveGames_shouldReturnGameList() {
        Mockito.when(gameRepository.saveAll(games)).thenReturn(games);

        List<Game> savedGames = gameService.saveGames(games);

        Assertions.assertThat(savedGames).isEqualTo(Arrays.asList(game1, game2, game3));
        Assertions.assertThat(savedGames.get(0)).isEqualTo(game1);
        Assertions.assertThat(savedGames.get(2).getName()).isEqualTo("Doom (2016)");

    }

    @Test
    public void testFindGame_shouldReturnGame() {
        Mockito.when(gameRepository.findById(1L)).thenReturn(Optional.of(game1));
        
        Game foundGame = gameService.findGame(1L);

        Assertions.assertThat(foundGame).isNotNull();
        Assertions.assertThat(foundGame.getId()).isGreaterThan(0L);
        Assertions.assertThat(foundGame.getName()).isEqualTo("Super Mario 64");
    }

    @Test
    public void testFindGame_shouldReturnNull() {
        Mockito.when(gameRepository.findById(1L)).thenReturn(Optional.empty());

        Game foundGame = gameService.findGame(1L);

        Assertions.assertThat(foundGame).isNull();
    }

    @Test
    public void testFindAllGames_shouldReturnGameList() {
        Mockito.when(gameRepository.findAll()).thenReturn(games);

        List<Game> foundGames = gameService.findAllGames();
        
        Assertions.assertThat(foundGames).isEqualTo(Arrays.asList(game1, game2, game3));
    }

    @Test
    public void testFindAllGames_shouldReturnNull() {
        Mockito.when(gameRepository.findAll()).thenReturn(null);

        List<Game> foundGames = gameService.findAllGames();

        Assertions.assertThat(foundGames).isNull();

    }   

}
