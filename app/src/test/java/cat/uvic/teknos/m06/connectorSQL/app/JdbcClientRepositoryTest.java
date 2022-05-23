package cat.uvic.teknos.m06.connectorSQL.app;

import cat.uvic.teknos.m06.connectorSQL.app.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.connectorSQL.app.Exception.ConnectionException;
import cat.uvic.teknos.m06.connectorSQL.app.Model.Client;
import cat.uvic.teknos.m06.connectorSQL.app.Repositories.JdbcClientRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcJdbcClientRepositoryTest {

    @Test
    void notThrowExcpetionOnInsert() throws ConnectionException{
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);


        Client cli = new Client(0,"karim","el Bouzzaoui del Moral", "Av. Olímpia 11B 5a 4rta");
        JdbcClientRepository rep = new JdbcClientRepository(exec.connection);

        assertDoesNotThrow(()->rep.save(cli));

        assertNotEquals(0,cli.getId());
    }

    @Test
    void notThrowExcpetionOnUpdate() throws ConnectionException{
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);


        Client cli = new Client(9,"Karim","el Bouzzaoui del Moral", "Av. Olímpia 11B 5a 4rta");
        JdbcClientRepository rep = new JdbcClientRepository(exec.connection);

        assertDoesNotThrow(()->rep.save(cli));

        assertNotEquals(0,cli.getId());
    }
    @Test
    void throwsClientException() throws ConnectionException {

        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);


        Client cli = new Client(-1,"Karim","el Bouzzaoui del Moral", "Av. Olímpia 11B 5a 4rta");
        JdbcClientRepository rep = new JdbcClientRepository(exec.connection);

        assertThrows(ClientExcpetion.class,()->rep.save(cli));

        assertNotEquals(0,cli.getId());

    }
}
