package com.ggpsgeorge.spring_user_gaming_list;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

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

    User testUser = User.builder()
    .id(1L)
    .userName("son_goku")
    .email("sonGoku@dbz.com")
    .password("Kamehameha")
    .games(games)
    .build();
    
    UserDTO testUserDTO = UserDTO.builder()
    .userName("son_goku")
    .games(games)
    .build();
    

    @Test
    public void testSaveUser_shouldReturnUser() {
        Mockito.when(userRepository.save(testUser)).thenReturn(testUser);

        User savedUser = userService.saveUser(testUser);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
        Assertions.assertThat(savedUser.getUserName()).isEqualTo("son_goku");
        Assertions.assertThat(savedUser.getGames().get(1).getName()).isEqualTo("Elden Ring");
    }

    // @Test
    // public void testFindUser_shouldReturnNull() {
    //     Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

    //     User savedUser = userService.findUser(1L);

    //     Assertions.assertThat(savedUser).isNull();
    // }

    @Test 
    public void testFindUser_shouldThrowNoSuchElementException(){
        Assertions.assertThatThrownBy(() -> {
            userService.findUser(1L);
        }).isInstanceOf(NoSuchElementException.class);
    }   

    @Test
    public void testRemoveUser() {
        // lenient() is necessary because of a unnecessaryStubbingException
        Mockito.lenient().when(userRepository.save(testUser)).thenReturn(testUser);

        userService.removeUser(testUser.getId());

        Assertions.assertThatThrownBy(() -> {
            userService.findUser(testUser.getId());
        }).isInstanceOf(NoSuchElementException.class);
        
        // Assertions.assertThat(userService.findUser(testUser.getId())).isNull();
    }

}
