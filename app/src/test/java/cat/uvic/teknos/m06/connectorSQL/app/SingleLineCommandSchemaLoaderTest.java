package cat.uvic.teknos.m06.connectorSQL.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleLineCommandSchemaLoaderTest {

    @Test
    void load() {
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/mysql","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");
        var singleCommandSchema = new SingleLineCommandSchemaLoader(schema,conn);

        singleCommandSchema.load();
    }
}