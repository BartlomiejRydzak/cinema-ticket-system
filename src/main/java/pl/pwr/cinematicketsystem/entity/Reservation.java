package pl.pwr.cinematicketsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservation")
@Getter
@Setter
public class Reservation {
    @Id
    @SequenceGenerator(name = "RESERVATION_ID_GENERATOR", sequenceName = "RESERVATION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESERVATION_ID_GENERATOR")
    private Integer id;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    public void addTicket(Ticket ticket){
        if(tickets == null) {
            tickets = new ArrayList<>();
        }

        tickets.add(ticket);
        ticket.setReservation(this);
    }
}
