import org.greenSnake.CRUD.PlanetCrudService;
import org.greenSnake.CRUD.TicketCrudService;
import org.greenSnake.data.Planet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlanetCrudServiceTest {
    PlanetCrudService service = new PlanetCrudService();
    Planet getAnyPlanet() {
        return service.listAll().stream().findAny().orElse(null);
    }
    @BeforeEach
    void setUp() {
        service = new PlanetCrudService();
    }

    @Test
    public void testThatCreateIsCorrect(){
        Planet planet = new Planet();
        planet.setId("NEW1");
        planet.setName("NewPlanet");
        service.create(planet);
        String med = service.getById(planet.getId());
        Assertions.assertEquals(planet.getName(),med);
    }

    @Test
    public void testGetById(){
        Planet planet = getAnyPlanet();
        String id = planet.getId();
        System.out.println(service.getById(id));
    }

    @Test
    public void testSetName(){
        Planet planet = getAnyPlanet();
        String id = planet.getId();
        service.setName(id,"newName");
        String actual = service.getById(planet.getId());
        Assertions.assertEquals("newName",actual);
    }
    @Test
    public void testThatDelete(){
        Planet planet = getAnyPlanet();
        TicketCrudService tickets = new TicketCrudService();
        planet.getFromTickets().forEach(tickets::isDelete);
        planet.getToTickets().forEach(tickets::isDelete);
        boolean actual = service.isDelete(planet);
        Assertions.assertTrue(actual);
    }
}
