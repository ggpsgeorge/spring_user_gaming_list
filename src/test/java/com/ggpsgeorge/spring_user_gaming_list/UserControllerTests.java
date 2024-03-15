package com.ggpsgeorge.spring_user_gaming_list;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureJsonTesters
@WebMvcTest(UserController.class)
public class UserControllerTests {
    
    @Autowired MockMvc mockMvc;
    @MockBean UserService userService;
    @MockBean GameService gameService;
    @Autowired ObjectMapper objectMapper;

    private static final String ENDPOINT = "/api/v1/users" ;
    private static final String CONTENT_TYPE = "application/json";

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
    .id(1L)
    .userName("son_goku")
    .games(games)
    .build();

    @Test
    public void testGetUser_shouldReturnUserDTO200Ok() 
        throws JsonProcessingException, Exception {

        Mockito.when(userService.findUser(testUser.getId())).thenReturn(testUser);
        
        String request = objectMapper.writeValueAsString(testUserDTO);
        ResultActions response = mockMvc.perform(get(ENDPOINT + "/1")
            .contentType(CONTENT_TYPE)
            .content(request));
        
        response.andExpect(status().isOk())
            .andExpect(jsonPath("$.userName", is(testUserDTO.getUserName())))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testAddUser_shouldReturnUserDTO201Created() 
        throws JsonProcessingException, Exception {
        
        Mockito.when(userService.saveUser(testUser)).thenReturn(testUser);
        
        String request = objectMapper.writeValueAsString(testUser);
        
        ResultActions response = mockMvc.perform(post(ENDPOINT + "/add")
            .contentType(CONTENT_TYPE)
            .content(request));
            
        response.andExpect(status().isCreated())
            .andExpect(jsonPath("$.userName", is(testUserDTO.getUserName())))
            .andDo(MockMvcResultHandlers.print());
    }
}
