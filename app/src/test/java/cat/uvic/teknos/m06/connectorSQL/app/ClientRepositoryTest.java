package cat.uvic.teknos.m06.connectorSQL.app;

import cat.uvic.teknos.m06.connectorSQL.app.Exception.ConnectionException;
import cat.uvic.teknos.m06.connectorSQL.app.Exception.ExecuteCommandException;
import cat.uvic.teknos.m06.connectorSQL.app.Model.Client;
import cat.uvic.teknos.m06.connectorSQL.app.Repositories.ClientRepository;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ClientRepositoryTest {

    @Test
    void notThrowExcpetion() throws ConnectionException, ExecuteCommandException, SQLException {
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/mysql","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);


        Client cli = new Client(0,"karim","el Bouzzaoui del Moral", "Av. Olímpia 11B 5a 4rta");
        ClientRepository rep = new ClientRepository(exec.connection);

        exec.load();

        assertEquals(0,cli.getId());

        assertDoesNotThrow(()->rep.save(cli));

        assertNotEquals(0,cli.getId());
    }
}
