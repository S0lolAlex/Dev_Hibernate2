package org.greenSnake.data;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


@Entity
@Data
public class Planet {
    @Id
    @Column(length = 50, nullable = false)
    private String id;
    @Column(length = 500,nullable = false)
    private String name;
    @OneToMany(mappedBy = "fromPlanet")
    private List<Ticket> fromTickets;
    @OneToMany(mappedBy = "toPlanet")
    private List<Ticket> toTickets;
}
