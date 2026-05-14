package pl.pwr.cinematicketsystem.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pwr.cinematicketsystem.dto.MovieRequest;
import pl.pwr.cinematicketsystem.dto.MovieResponse;
import pl.pwr.cinematicketsystem.entity.Movie;
import pl.pwr.cinematicketsystem.exception.ResourceNotFoundException;
import pl.pwr.cinematicketsystem.repository.MovieRepository;

@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    @Mock
    MovieRepository movieRepository;

    @Test
    void addMovie_savesAndMaps() {
        MovieRequest req = mock(MovieRequest.class);
        when(req.getTitle()).thenReturn("T");
        when(req.getDescription()).thenReturn("D");
        when(req.getDurationMinutes()).thenReturn(90);
        when(req.getImageUrl()).thenReturn("img");

        Movie saved = Movie.builder()
            .id(10)
            .title("T")
            .description("D")
            .durationMinutes(90)
            .imageUrl("img")
            .build();

        when(movieRepository.save(any())).thenReturn(saved);

        MovieServiceImpl svc = new MovieServiceImpl(movieRepository);
        MovieResponse res = svc.addMovie(req);

        assertEquals(saved.getId(), res.getId());
        assertEquals("T", res.getTitle());
        assertEquals(90, res.getDurationMinutes());
        verify(movieRepository, times(1)).save(any());
    }

    @Test
    void getMovieById_throws_whenNotFound() {
        when(movieRepository.findById(1)).thenReturn(
            java.util.Optional.empty()
        );
        MovieServiceImpl svc = new MovieServiceImpl(movieRepository);
        assertThrows(ResourceNotFoundException.class, () ->
            svc.getMovieById(1)
        );
    }

    @Test
    void mapToResponse_includesShows_whenPresent() {
        Movie m = Movie.builder()
            .id(2)
            .title("X")
            .description("Y")
            .durationMinutes(100)
            .imageUrl("u")
            .build();
        // no shows
        MovieServiceImpl svc = new MovieServiceImpl(movieRepository);
        var resp = svc.mapToResponse(m);
        assertEquals(m.getTitle(), resp.getTitle());
        assertNotNull(resp.getShows());
        assertTrue(resp.getShows().isEmpty());
    }
}
