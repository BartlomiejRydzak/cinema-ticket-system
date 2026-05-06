package pl.pwr.cinematicketsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "show")
public class Show {

    @Id
    @SequenceGenerator(name = "SHOW_ID_GENERATOR", sequenceName = "SHOW_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHOW_ID_GENERATOR")
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "room")
    private Integer room;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

}