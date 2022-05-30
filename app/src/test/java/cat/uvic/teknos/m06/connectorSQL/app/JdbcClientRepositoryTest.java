package cat.uvic.teknos.m06.connectorSQL.app;

import cat.uvic.teknos.m06.connectorSQL.app.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.connectorSQL.app.Exception.ConnectionException;
import cat.uvic.teknos.m06.connectorSQL.utilities.models.Client;
import cat.uvic.teknos.m06.connectorSQL.app.Repositories.JdbcClientRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcClientRepositoryTest {

    @Test
    void notThrowExcpetionOnInsert() throws ConnectionException{
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);

        JdbcClientRepository rep = new JdbcClientRepository(exec.connection);
        Client cli = new Client(0,"karim","el Bouzzaoui del Moral", "Av. Olímpia 11B 5a 4rta");
        Client cli2 = new Client(0,"Pol","Vilarrassa", "Carrer de Sant Antoni 2n 1era");


        assertDoesNotThrow(()->rep.save(cli));

        assertNotEquals(0,cli.getId());
    }

    @Test
    void notThrowExcpetionOnUpdate() throws ConnectionException{
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);


        Client cli = new Client(0,"update test before","el Bouzzaoui del Moral", "Av. Olímpia 11B 5a 4rta");

        JdbcClientRepository rep = new JdbcClientRepository(exec.connection);

        assertDoesNotThrow(()->rep.save(cli));

        cli.setName("update test after");

        assertDoesNotThrow(()->rep.save(cli));

        assertNotEquals(0,cli.getId());
    }

    @Test
    void notThrowExeptionOnDelete() throws ConnectionException, ClientExcpetion {
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);

        Client cli;
        JdbcClientRepository rep = new JdbcClientRepository(exec.connection);
        Client cli2 = new Client(0,"Pol","Vilarrassa", "Carrer de Sant Antoni 2n 1era");
        assertDoesNotThrow(()->rep.save(cli2));


        assertDoesNotThrow(()->rep.delete(cli2));
    }

    @Test
    void notThrowExcpetionOnSelect() throws ConnectionException, ClientExcpetion {
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);


        Client cli;// = new Client(12,"Karim","el Bouzzaoui del Moral", "Av. Olímpia 11B 5a 4rta");

        JdbcClientRepository rep = new JdbcClientRepository(exec.connection);
        cli = rep.getAll().get(0);

        assertDoesNotThrow(()->{
            var res = rep.getById(cli.getId());
            System.out.println(res);
        }
        );
    }
    @Test
    void notThrowExcpetionOnSelectAll() throws ConnectionException{
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);


        //Client cli = new Client(12,"Karim","el Bouzzaoui del Moral", "Av. Olímpia 11B 5a 4rta");
        JdbcClientRepository rep = new JdbcClientRepository(exec.connection);

        assertDoesNotThrow(()->{
                    var res = rep.getAll();
                    System.out.println(res);
                }
        );
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
