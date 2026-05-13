package pl.pwr.cinematicketsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pwr.cinematicketsystem.entity.Room;
import pl.pwr.cinematicketsystem.exception.ResourceNotFoundException;
import pl.pwr.cinematicketsystem.repository.RoomRepository;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public Room getRoomById(Integer id) {
        return roomRepository
            .findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException("Room not found with id: " + id)
            );
    }
}
