package cat.uvic.teknos.m06.connectorSQL.app;

import cat.uvic.teknos.m06.connectorSQL.app.Exception.ConnectionException;
import cat.uvic.teknos.m06.connectorSQL.app.Exception.ExecuteCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Jdbc1ExecuteCommandsTest {

    @Test
    void notThrowException() {

        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/mysql","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");
        final ExecuteCommands[] executeCommands = new ExecuteCommands[1];
        assertDoesNotThrow(()-> executeCommands[0] = new ExecuteCommands(schema,conn));


        assertDoesNotThrow(()-> executeCommands[0].load());
    }

    @Test
    void throwExecuteCommandsException() throws ConnectionException{
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/mysql","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema_BAD.xml");
        ExecuteCommands executeCommands;

        assertDoesNotThrow( ()->new ExecuteCommands(schema,conn));

        executeCommands = new ExecuteCommands(schema,conn);
        assertThrows(ExecuteCommandException.class,()->executeCommands.load());
    }

    @Test
    void throwsConnectionException() throws ConnectionException {
        ConnectionProperties conn = new ConnectionProperties("","","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertThrows(ConnectionException.class,()->new ExecuteCommands(schema,conn));
    }

}