package com.farmcollector.service;

import com.farmcollector.model.Season;
import com.farmcollector.repository.SeasonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SeasonServiceTest {

    @Mock
    private SeasonRepository seasonRepository;

    @InjectMocks
    private SeasonService seasonService;

    @Test
    public void testGetAllSeasons() {
        Season season = new Season(1L, "Spring 2024", null, null);
        when(seasonRepository.findAll()).thenReturn(List.of(season));

        List<Season> seasons = seasonService.getAllSeasons();
        assertEquals(1, seasons.size());
        assertEquals("Spring 2024", seasons.get(0).getSeasonName());
    }

    @Test
    public void testGetSeasonById() {
        Season season = new Season(1L, "Spring 2024", null, null);
        when(seasonRepository.findById(1L)).thenReturn(Optional.of(season));

        Optional<Season> foundSeason = seasonService.getSeasonById(1L);
        assertTrue(foundSeason.isPresent());
        assertEquals("Spring 2024", foundSeason.get().getSeasonName());
    }

    @Test
    public void testSaveSeason() {
        Season season = new Season(1L, "Spring 2024", null, null);
        when(seasonRepository.save(season)).thenReturn(season);

        Season savedSeason = seasonService.saveSeason(season);
        assertEquals("Spring 2024", savedSeason.getSeasonName());
    }

    @Test
    public void testDeleteSeason() {
        seasonService.deleteSeason(1L);
        verify(seasonRepository, times(1)).deleteById(1L);
    }
}
