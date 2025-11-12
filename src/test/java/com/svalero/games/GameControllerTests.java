package com.svalero.games;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svalero.games.controller.GameController;
import com.svalero.games.dto.GameOutDto;
import com.svalero.games.service.GameService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
public class GameControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GameService gameService;

    @MockitoBean
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAll() throws Exception {
        List<GameOutDto> gamesOutDto = List.of(
                new GameOutDto(1, "7 Days to die", "Survival game", "shooter", "survival"),
                new GameOutDto(2, "FIFA 2025", "Football game", "sport", "sports")
        );

        when(gameService.findAll("")).thenReturn(gamesOutDto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/games")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        List<GameOutDto> gamesListResponse = objectMapper.readValue(jsonResponse, new TypeReference<>(){});

        assertNotNull(gamesListResponse);
        assertEquals(2, gamesListResponse.size());
        assertEquals("7 Days to die", gamesListResponse.getFirst().getName());
    }

    @Test
    public void testGetAllByCategory() throws Exception {
        List<GameOutDto> gamesOutDto = List.of(
                new GameOutDto(2, "FIFA 2025", "Football game", "sport", "sports"),
                new GameOutDto(3, "FIFA 2026", "Football game", "sport", "sports")
        );

        when(gameService.findAll("sports")).thenReturn(gamesOutDto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/games")
                        .queryParam("category", "sports")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        List<GameOutDto> gamesListResponse = objectMapper.readValue(jsonResponse, new TypeReference<>(){});

        assertNotNull(gamesListResponse);
        assertEquals(2, gamesListResponse.size());
        assertEquals("FIFA 2025", gamesListResponse.getFirst().getName());
    }
}
