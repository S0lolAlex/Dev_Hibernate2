import org.greenSnake.CRUD.ClientCrudService;
import org.greenSnake.CRUD.TicketCrudService;
import org.greenSnake.data.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClientCrudServiceTest {
    ClientCrudService service = new ClientCrudService();
    Client getAnyClient() {
        return service.listAll().stream().findAny().orElse(null);
    }
    @BeforeEach
    void setUp() {
        service = new ClientCrudService();
    }
    @Test
    public void testThatCreateIsCorrect(){
        Client client = new Client();
        client.setName("Pavel");
        service.create(client);
        String anton = service.getById(client.getId());
        Assertions.assertEquals(client.getName(),anton);
    }

    @Test
    public void testGetById(){
        Client client = getAnyClient();
        System.out.println("name = " + service.getById(client.getId()));

    }

    @Test
    public void testSetName(){
        Client client = getAnyClient();
        long id = client.getId();
        service.setName(id,"newName");
        System.out.println(service.getById(id));
    }
    @Test
    public void testThatDelete(){
        Client client = getAnyClient();
        TicketCrudService tickets = new TicketCrudService();
        client.getTickets().forEach(tickets::isDelete);
        boolean actual = service.isDelete(client);
        Assertions.assertTrue(actual);
    }
}
