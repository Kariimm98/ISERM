package cat.uvic.teknos.m06.iserm.domain;

import cat.uvic.teknos.m06.iserm.domain.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.iserm.domain.Exception.ProductException;
import cat.uvic.teknos.m06.iserm.domain.Repository.JPAClientRepository;
import cat.uvic.teknos.m06.iserm.domain.model.Client;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

public class JPAClientRepositoryTest {
    private static EntityManagerFactory entity ;
    private static JPAClientRepository repository;

    @BeforeAll
    static void setUp(){
        entity = Persistence.createEntityManagerFactory("ISERM");
        repository = new JPAClientRepository(entity);
    }

    @Test
    public void saveInsert() throws Exception {
        var repos = new JPAClientRepository(entity);
        var client = new Client();
        client.setName("Pepe");
        client.setAddress("add");
        client.setSurname("proves");

        assertDoesNotThrow(()->{
            repos.save(client);
        });

        assertTrue(client.getId()>0);

    }

    @Test
    public void saveUpdate(){
        var repos = new JPAClientRepository(entity);
        var client = new Client();
        client.setId(1);
        client.setName("Pepe2");
        client.setAddress("add2");
        client.setSurname("proves2");

        assertDoesNotThrow(()->{
            repos.save(client);
        });
        assertTrue(client.getId()>0);
    }

    @Test
    void FindById() throws Exception {

        var client = repository.getById(1);

        assertTrue(client.getId()>0);
    }

    @Test
    void selectAll() throws ClientExcpetion, ProductException {
        var list = repository.getAll();
        assertNotNull(list);
    }

    @Test
    void deleteOne() throws ClientExcpetion, ProductException {
        var client = repository.getById(2);
        repository.delete(client);
        client = repository.getById(2);
        assertNull(client);
    }
}
