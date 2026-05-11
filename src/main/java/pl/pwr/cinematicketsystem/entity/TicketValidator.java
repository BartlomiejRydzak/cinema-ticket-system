package pl.pwr.cinematicketsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ticket_validator")
@Getter
@Setter
public class TicketValidator {

    @Id
    @SequenceGenerator(name = "TICKET_VALIDATOR_ID_GENERATOR",sequenceName = "TICKET_VALIDATOR_SEQ",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TICKET_VALIDATOR_ID_GENERATOR")
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;
}
