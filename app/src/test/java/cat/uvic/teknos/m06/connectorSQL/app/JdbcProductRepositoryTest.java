package cat.uvic.teknos.m06.connectorSQL.app;

import cat.uvic.teknos.m06.connectorSQL.app.Exception.ConnectionException;
import cat.uvic.teknos.m06.connectorSQL.app.Exception.ProductException;
import cat.uvic.teknos.m06.connectorSQL.utilities.models.Product;
import cat.uvic.teknos.m06.connectorSQL.app.Repositories.JdbcProductRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcProductRepositoryTest {

    @Test
    void NotThrowsExceptionOnInsert() throws ConnectionException {
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);

        JdbcProductRepository rep = new JdbcProductRepository(exec.connection);
        Product product = new Product(0,"logitech mouse G-403","Mouse optico 400-16000 dpi ajustables",(float)43.98);
        Product product2 = new Product(0,"logitech mouse G-203","Mouse optico 800-8000 dpi ajustables",(float)23.98);
        assertDoesNotThrow(()->rep.save(product));
        assertDoesNotThrow(()->rep.save(product2));

        assertNotEquals(0,product.getId());
    }

    @Test
    void NotThrowsExceptionOnSelectAll() throws ConnectionException {
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);

        JdbcProductRepository rep = new JdbcProductRepository(exec.connection);
        assertDoesNotThrow(()->{
            var list = rep.getAll().get(0);
            System.out.println(list);
        });
    }

    @Test
    void NotThrowsExceptionOnSelect() throws ConnectionException, ProductException {
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);

        JdbcProductRepository rep = new JdbcProductRepository(exec.connection);
        Product prod = new Product(0,"logitech mouse G-403","Mouse optico 400-16000 dpi ajustables",(float)43.98);
        assertDoesNotThrow(()->rep.save(prod));
        assertDoesNotThrow(()->{
            rep.getById(prod.getId());
        });
    }

    @Test
    void notThrowExceptionOnUpdate() throws ConnectionException, ProductException {
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);

        JdbcProductRepository rep = new JdbcProductRepository(exec.connection);
        Product product = new Product(0,"logitech mouse G-403","Mouse optico 400-16000 dpi ajustables",(float)43.98);
        assertDoesNotThrow(()->rep.save(product));

        product.setPrice((float) 99.99);
        assertDoesNotThrow(()->rep.save(product));
        assertNotEquals(0,product.getId());

    }

    @Test
    void notThrowExceptionOnDelete() throws ConnectionException, ProductException {
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);

        JdbcProductRepository rep = new JdbcProductRepository(exec.connection);
        Product product = new Product(0,"logitech mouse G-403","Mouse optico 400-16000 dpi ajustables",(float)43.98);
        assertDoesNotThrow(()->rep.save(product));

        assertDoesNotThrow(()->rep.delete(product));

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
