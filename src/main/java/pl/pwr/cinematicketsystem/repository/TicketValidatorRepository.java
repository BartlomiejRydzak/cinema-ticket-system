package pl.pwr.cinematicketsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.cinematicketsystem.entity.TicketValidator;

public interface TicketValidatorRepository extends JpaRepository<TicketValidator, Integer> {
    TicketValidator findByUsername(String username);
}
