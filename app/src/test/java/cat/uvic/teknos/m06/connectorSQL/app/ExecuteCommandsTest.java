package cat.uvic.teknos.m06.connectorSQL.app;

import cat.uvic.teknos.m06.connectorSQL.app.Exception.ExecuteCommandException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExecuteCommandsTest {

    @Test
    void notThrowException() {

            ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/mysql","root","");
            XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");
            var singleCommandSchema = new ExecuteCommands(schema,conn);

        assertDoesNotThrow(()->singleCommandSchema.load());
    }
}