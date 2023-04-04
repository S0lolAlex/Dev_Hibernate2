package org.greenSnake.data;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Planet {
    @Id
    @Column(length = 50, nullable = false)
    private String id;
    @Column(length = 500,nullable = false)
    private String name;
    @OneToMany(mappedBy = "fromPlanet",cascade = CascadeType.ALL)
    private List<Ticket> fromTickets = new ArrayList<>();
    @OneToMany(mappedBy = "toPlanet",cascade = CascadeType.ALL)
    private List<Ticket> toTickets = new ArrayList<>();
}
