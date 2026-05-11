package pl.pwr.cinematicketsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.descriptor.jdbc.SmallIntJdbcType;

@Entity
@Table(name = "users")
@Getter
@Setter
public class TicketValidator {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private Integer enabled;
}
