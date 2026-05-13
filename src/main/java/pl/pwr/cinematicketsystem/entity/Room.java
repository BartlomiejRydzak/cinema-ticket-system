package pl.pwr.cinematicketsystem.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "room")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @SequenceGenerator(
        name = "ROOM_ID_GENERATOR",
        sequenceName = "ROOM_SEQ",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "ROOM_ID_GENERATOR"
    )
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "room")
    private List<Show> shows;

    @OneToMany(mappedBy = "room")
    private List<Seat> seats;
}
