package pl.pwr.cinematicketsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.cinematicketsystem.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
