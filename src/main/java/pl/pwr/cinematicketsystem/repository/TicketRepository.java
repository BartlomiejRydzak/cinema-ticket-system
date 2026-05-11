package pl.pwr.cinematicketsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.cinematicketsystem.entity.Seat;
import pl.pwr.cinematicketsystem.entity.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByShowId(Integer showId);
    Ticket findByCode(String code);
}
