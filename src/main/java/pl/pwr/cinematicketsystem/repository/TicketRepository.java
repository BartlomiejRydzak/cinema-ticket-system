package pl.pwr.cinematicketsystem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.cinematicketsystem.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByShowId(Integer showId);
    Ticket findByCode(String code);
}
