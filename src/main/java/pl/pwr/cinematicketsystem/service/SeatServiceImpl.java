package pl.pwr.cinematicketsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pwr.cinematicketsystem.entity.Seat;
import pl.pwr.cinematicketsystem.exception.ResourceNotFoundException;
import pl.pwr.cinematicketsystem.repository.SeatRepository;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    @Override
    public Seat getSeatById(Integer id) {
        return seatRepository
            .findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException("Seat not found with id: " + id)
            );
    }
}
