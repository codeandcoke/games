package com.svalero.games;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.svalero.games.domain.Game;
import com.svalero.games.repository.GameRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GameControllerIntegrationTest {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        gameRepository.save(Game.builder().name("testGame1").build());
        gameRepository.save(Game.builder().name("testGame2").build());
    }

    @AfterEach
    public void clean() {
        gameRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void shouldDeleteGame() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/games/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(2)
    public void shouldGetAllGames() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/games"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @Order(3)
    public void shouldAddGame() throws Exception {
        Game game = new Game();
        game.setName("testGame");

        String json = objectMapper.writeValueAsString(game);

        mockMvc.perform(post("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("testGame"));
    }
}
