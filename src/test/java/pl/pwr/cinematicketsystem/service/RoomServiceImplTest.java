package pl.pwr.cinematicketsystem.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.pwr.cinematicketsystem.entity.Room;
import pl.pwr.cinematicketsystem.exception.ResourceNotFoundException;
import pl.pwr.cinematicketsystem.repository.RoomRepository;

@ExtendWith(MockitoExtension.class)
class RoomServiceImplTest {

    @Mock
    RoomRepository roomRepository;

    @Test
    void getRoomById_returnsRoom() {
        Room r = new Room(); r.setId(5);
        when(roomRepository.findById(5)).thenReturn(java.util.Optional.of(r));
        RoomServiceImpl svc = new RoomServiceImpl(roomRepository);
        Room got = svc.getRoomById(5);
        assertEquals(5, got.getId());
    }

    @Test
    void getRoomById_throws_whenMissing() {
        when(roomRepository.findById(99)).thenReturn(java.util.Optional.empty());
        RoomServiceImpl svc = new RoomServiceImpl(roomRepository);
        assertThrows(ResourceNotFoundException.class, () -> svc.getRoomById(99));
    }
}
