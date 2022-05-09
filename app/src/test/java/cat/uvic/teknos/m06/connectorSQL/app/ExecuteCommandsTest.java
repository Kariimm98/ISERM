package cat.uvic.teknos.m06.connectorSQL.app;

import cat.uvic.teknos.m06.connectorSQL.app.Exception.ConnectionException;
import cat.uvic.teknos.m06.connectorSQL.app.Exception.ExecuteCommandException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ExecuteCommandsTest {

    @Test
    void notThrowException() {

        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/mysql","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");
        var executeCommands = new ExecuteCommands(schema,conn);

        assertDoesNotThrow(()->executeCommands.load());
    }

    @Test
    void throwExecuteCommandsException(){
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/mysql","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema_BAD.xml");
        var executeCommands = new ExecuteCommands(schema,conn);

        assertThrows(ExecuteCommandException.class,()->executeCommands.load());
    }

    @Test
    void throwsConnectionException(){
        ConnectionProperties conn = new ConnectionProperties("","","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");
        var executeCommands = new ExecuteCommands(schema,conn);

        assertThrows(ConnectionException.class,()->executeCommands.load());
    }
}