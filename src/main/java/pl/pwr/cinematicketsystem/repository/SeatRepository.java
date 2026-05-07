package pl.pwr.cinematicketsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.cinematicketsystem.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
