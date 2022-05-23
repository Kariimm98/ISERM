package cat.uvic.teknos.m06.connectorSQL.app;

import cat.uvic.teknos.m06.connectorSQL.app.Exception.ConnectionException;
import cat.uvic.teknos.m06.connectorSQL.app.Exception.ProductException;
import cat.uvic.teknos.m06.connectorSQL.app.Model.Product;
import cat.uvic.teknos.m06.connectorSQL.app.Repositories.JdbcProductRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcJdbcProductRepositoryTest {

    @Test
    void NotThrowsExceptionOnInsert() throws ConnectionException {
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);


        Product product = new Product(0,"logitech mouse G-403","Mouse optico 400-16000 dpi ajustables",(float)43.98);
        JdbcProductRepository rep = new JdbcProductRepository(exec.connection);

        assertDoesNotThrow(()->rep.save(product));

        assertNotEquals(0,product.getId());
    }

    @Test
    void notThrowExcpetionOnUpdate() throws ConnectionException{
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);

        Product product = new Product(1,"logitech mouse G-403","Mouse optico 400-16000 dpi ajustables",(float)24.12);
        JdbcProductRepository rep = new JdbcProductRepository(exec.connection);

        assertDoesNotThrow(()->rep.save(product));

        assertNotEquals(0,product.getId());
    }

    @Test
    void ThrowProductException() throws ConnectionException{
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);

        Product product = new Product(-1,"logitech mouse G-403","Mouse optico 400-16000 dpi ajustables",(float)24.12);
        JdbcProductRepository rep = new JdbcProductRepository(exec.connection);


        assertThrows(ProductException.class,()->rep.save(product));

        assertNotEquals(0,product.getId());
    }
}
