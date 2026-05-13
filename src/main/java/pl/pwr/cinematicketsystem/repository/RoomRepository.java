package pl.pwr.cinematicketsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.cinematicketsystem.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {}
