package cat.uvic.teknos.m06.connectorSQL.app;
import cat.uvic.teknos.m06.iserm.domain.Repository.JPAClientRepository;

import cat.uvic.teknos.m06.iserm.domain.model.Client;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.persistence.Persistence;

public class JPAClientRepositoryTest {

    @Test
    public void NotThrowError() throws Exception {
        var entity = Persistence.createEntityManagerFactory("ISERM");

        var repos = new JPAClientRepository(entity);
        //var client = new Client(0,"Pep","patata","proves");
        var client = new Client();
        client.setName("pepe");

        assertDoesNotThrow(()->{
            repos.save(client);
        });


        assertTrue(client.getId()>0);

    }
}
