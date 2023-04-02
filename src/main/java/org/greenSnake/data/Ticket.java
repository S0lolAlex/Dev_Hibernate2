package org.greenSnake.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "client_id",nullable = false)
    private Client client;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "from_planet_id",nullable = false)
    private Planet fromPlanet;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "to_planet_id",nullable = false)
    private Planet toPlanet;
}
