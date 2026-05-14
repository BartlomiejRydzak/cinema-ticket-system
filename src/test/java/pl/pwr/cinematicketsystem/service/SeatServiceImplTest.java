package pl.pwr.cinematicketsystem.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.pwr.cinematicketsystem.entity.Seat;
import pl.pwr.cinematicketsystem.exception.ResourceNotFoundException;
import pl.pwr.cinematicketsystem.repository.SeatRepository;

@ExtendWith(MockitoExtension.class)
class SeatServiceImplTest {

    @Mock
    SeatRepository seatRepository;

    @Test
    void getSeatById_returnsSeat() {
        Seat s = new Seat(); s.setId(7);
        when(seatRepository.findById(7)).thenReturn(java.util.Optional.of(s));
        SeatServiceImpl svc = new SeatServiceImpl(seatRepository);
        Seat got = svc.getSeatById(7);
        assertEquals(7, got.getId());
    }

    @Test
    void getSeatById_throws_whenMissing() {
        when(seatRepository.findById(123)).thenReturn(java.util.Optional.empty());
        SeatServiceImpl svc = new SeatServiceImpl(seatRepository);
        assertThrows(ResourceNotFoundException.class, () -> svc.getSeatById(123));
    }
}
