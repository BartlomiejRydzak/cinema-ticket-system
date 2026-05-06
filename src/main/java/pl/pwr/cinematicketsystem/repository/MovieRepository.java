package pl.pwr.cinematicketsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.cinematicketsystem.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
