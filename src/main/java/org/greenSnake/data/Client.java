package org.greenSnake.data;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", length = 200, nullable = false)
    private String name;
    @OneToMany(mappedBy = "client",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<Ticket> tickets = new ArrayList<>();
}
