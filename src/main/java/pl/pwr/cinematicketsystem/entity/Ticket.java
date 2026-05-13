package pl.pwr.cinematicketsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @SequenceGenerator(
        name = "TICKET_ID_GENERATOR",
        sequenceName = "TICKET_SEQ",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "TICKET_ID_GENERATOR"
    )
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private TicketState state;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @Column(name = "code")
    private String code;
}
