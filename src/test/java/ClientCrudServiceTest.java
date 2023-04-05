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
        Client pavel = service.getById(client.getId());
        Assertions.assertEquals(client.getName(),pavel.getName());
    }

    @Test
    public void testGetById(){
        Client client = getAnyClient();
        long id = client.getId();
        System.out.println(service.getById(id));
    }

    @Test
    public void testUpdate(){
        Client client = getAnyClient();
        long id = client.getId();
        client.setName("newName");
        service.update(client);
        Assertions.assertEquals(service.getById(id).getName(),client.getName());
    }
    @Test
    public void testThatDelete(){
        Client client = getAnyClient();
        long id = client.getId();
        service.delete(client);
        Assertions.assertEquals(null, service.getById(id));
    }
}
