package pl.pwr.cinematicketsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ticket")
@Getter
@Setter
public class Ticket {

    @Id
    @SequenceGenerator(name = "TICKET_ID_GENERATOR", sequenceName = "TICKET_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TICKET_ID_GENERATOR")
    private Integer id;

    private Show show;

    private Seat seat;
}
