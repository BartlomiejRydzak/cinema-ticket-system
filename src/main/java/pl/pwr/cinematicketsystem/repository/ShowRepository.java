package pl.pwr.cinematicketsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.cinematicketsystem.entity.Show;

public interface ShowRepository extends JpaRepository<Show, Integer> {}
