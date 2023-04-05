import org.greenSnake.CRUD.ClientCrudService;
import org.greenSnake.CRUD.PlanetCrudService;
import org.greenSnake.CRUD.TicketCrudService;
import org.greenSnake.data.Client;
import org.greenSnake.data.Planet;
import org.greenSnake.data.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;


public class TicketCrudServiceTest {
    TicketCrudService ticketCrudService;
    ClientCrudService clientCrudService;
    PlanetCrudService planetCrudService;

    Client getAnyClient() {
        return clientCrudService.listAll().stream().findAny().orElse(null);
    }

    Planet getAnyPlanet() {
        return planetCrudService.listAll().stream().findAny().orElse(null);
    }

    Ticket getAnyTicket() {
        return ticketCrudService.listAll().stream().findAny().orElse(null);
    }

    @BeforeEach
    void setUp() {
        ticketCrudService = new TicketCrudService();
        clientCrudService = new ClientCrudService();
        planetCrudService = new PlanetCrudService();
    }

    @Test
    void create() {
        Ticket ticket = new Ticket();
        ticket.setClient(getAnyClient());
        ticket.setFromPlanet(getAnyPlanet());
        ticket.setToPlanet(getAnyPlanet());

        ticketCrudService.create(ticket);

        Ticket createdTicked = ticketCrudService.getById(ticket.getId());
        System.out.println("createdTicked = " + createdTicked);
    }

    @Test
    void createTestNullClient() {
        Ticket ticket = new Ticket();
        ticket.setFromPlanet(getAnyPlanet());
        ticket.setToPlanet(getAnyPlanet());
        ticket.setCreatedAt(new Date());

        Assertions.assertThrows(IllegalStateException.class, () -> new TicketCrudService().create(ticket));
    }

    @Test
    void createTestNullFromPlanet() {
        Ticket ticket = new Ticket();
        ticket.setClient(getAnyClient());
        ticket.setToPlanet(getAnyPlanet());
        ticket.setCreatedAt(new Date());

        Assertions.assertThrows(IllegalStateException.class, () -> new TicketCrudService().create(ticket));
    }

    @Test
    void createTestNullToPlanet() {
        Ticket ticket = new Ticket();
        ticket.setClient(getAnyClient());
        ticket.setCreatedAt(new Date());
        ticket.setFromPlanet(getAnyPlanet());

        Assertions.assertThrows(IllegalStateException.class, () -> new TicketCrudService().create(ticket));
    }

    @Test
    void get() {
        Ticket ticket = ticketCrudService.getById(getAnyTicket().getId());
        System.out.println("ticket = " + ticket);
    }

    @Test
    void getAll() {
        System.out.println("service.getAll() = " + ticketCrudService.listAll());
    }

    @Test
    void update() {
        Ticket ticket = getAnyTicket();
        System.out.println("ticket = " + ticket);
        long id = ticket.getId();

        Ticket ticketUpdated = ticketCrudService.getById(id);
        ticket.setClient(getAnyClient());
        ticket.setToPlanet(getAnyPlanet());
        ticketCrudService.update(ticketUpdated);
    }

    @Test
    void delete() {
        Ticket ticket = new TicketCrudService().listAll().stream().findFirst().orElse(null);
        System.out.println("ticket = " + ticket);
        long id = ticket.getId();
        ticketCrudService.isDelete(ticket);
        Assertions.assertEquals(null, ticketCrudService.getById(id));
    }
}
