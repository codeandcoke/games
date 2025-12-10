package com.svalero.games;

import com.svalero.games.domain.Game;
import com.svalero.games.dto.GameOutDto;
import com.svalero.games.repository.GameRepository;
import com.svalero.games.service.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceTests {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testFindAll() {
        List<Game> mockGameList = List.of(
                new Game(1, "7 Days to die", "Survival game", "shooter", LocalDate.now(), 100, "survival", null, null),
                new Game(2, "FIFA 2025", "Football game", "sport", LocalDate.now(), 60, "sports", null, null)
        );
        List<GameOutDto> modelMapperOut = List.of(
                new GameOutDto(1, "7 Days to die", "Survival game", "shooter", "survival"),
                new GameOutDto(2, "FIFA 2025", "Football game", "sport", "sports")
        );

        when(gameRepository.findAll()).thenReturn(mockGameList);
        when(modelMapper.map(mockGameList, new TypeToken<List<GameOutDto>>() {}.getType())).thenReturn(modelMapperOut);

        List<GameOutDto> actualGameList = gameService.findAll("");
        assertEquals(2, actualGameList.size());
        assertEquals("7 Days to die", actualGameList.getFirst().getName());
        assertEquals("FIFA 2025", actualGameList.getLast().getName());

        verify(gameRepository, times(1)).findAll();
        verify(gameRepository, times(0)).findByCategoryOrderByNameDesc("");
    }

    @Test
    public void testFindAllByCategory() {
        List<Game> mockGameList = List.of(
                new Game(1, "7 Days to die", "Survival game", "shooter", LocalDate.now(), 100, "survival", null, null),
                new Game(2, "FIFA 2025", "Football game", "sport", LocalDate.now(), 60, "sports", null, null),
                new Game(3, "FIFA 2026", "Football game", "sport", LocalDate.now(), 60, "sports", null, null)
        );
        List<GameOutDto> mockModelMapperOut = List.of(
                new GameOutDto(2, "FIFA 2025", "Football game", "sport", "sports"),
                new GameOutDto(3, "FIFA 2026", "Football game", "sport", "sports")
        );

        when(gameRepository.findByCategoryOrderByNameDesc("sports")).thenReturn(mockGameList);
        when(modelMapper.map(mockGameList, new TypeToken<List<GameOutDto>>() {}.getType())).thenReturn(mockModelMapperOut);

        List<GameOutDto> actualGameList = gameService.findAll("sports");
        assertEquals(2, actualGameList.size());
        assertEquals("FIFA 2025", actualGameList.getFirst().getName());
        assertEquals("FIFA 2026", actualGameList.getLast().getName());

        verify(gameRepository, times(0)).findAll();
        verify(gameRepository, times(1)).findByCategoryOrderByNameDesc("sports");
    }
}
