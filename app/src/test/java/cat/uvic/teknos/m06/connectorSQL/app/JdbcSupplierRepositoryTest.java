package cat.uvic.teknos.m06.connectorSQL.app;

import cat.uvic.teknos.m06.connectorSQL.app.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.connectorSQL.app.Exception.ConnectionException;
import cat.uvic.teknos.m06.connectorSQL.app.Exception.ProductException;
import cat.uvic.teknos.m06.connectorSQL.app.Exception.SupplierException;
import cat.uvic.teknos.m06.connectorSQL.app.Model.Product;
import cat.uvic.teknos.m06.connectorSQL.app.Model.Supplier;
import cat.uvic.teknos.m06.connectorSQL.app.Repositories.JdbcProductRepository;
import cat.uvic.teknos.m06.connectorSQL.app.Repositories.JdbcSupplierRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcSupplierRepositoryTest {

    @Test
    void NotThrowsExceptionOnInsert() throws ConnectionException {
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);

        JdbcSupplierRepository rep = new JdbcSupplierRepository(exec.connection);
        Supplier supp = new Supplier(0,"Lenovo","Calle Santa Hortensia 26, 28002, Madrid");
        Supplier supp2 = new Supplier(0,"HP","CALLE DE JESUS SERRA SANTAMANS 8, 08174, Sant Cugat DEL Valles (Barcelona) · ~51,3 km");
        assertDoesNotThrow(()->rep.save(supp));
        assertDoesNotThrow(()->rep.save(supp2));

        assertNotEquals(0,supp.getId());
        assertNotEquals(0,supp.getId());
    }

    @Test
    void NotThrowsExceptionOnSelectAll() throws ConnectionException {
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);

        JdbcSupplierRepository rep = new JdbcSupplierRepository(exec.connection);
        assertDoesNotThrow(()->{
            var list = rep.getAll();
        });
    }

    @Test
    void NotThrowsExceptionOnSelect() throws ConnectionException, ClientExcpetion {
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);

        JdbcSupplierRepository rep = new JdbcSupplierRepository(exec.connection);
        var prod = rep.getAll().get(0);
        assertDoesNotThrow(()->{
            rep.getById(prod.getId());
        });
    }

    @Test
    void notThrowExceptionOnUpdate() throws ConnectionException, ClientExcpetion {
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);

        JdbcSupplierRepository rep = new JdbcSupplierRepository(exec.connection);

        Supplier supplier = new Supplier(0,"Lenovo","Calle Santa Hortensia 26, 28002, Madrid");
        assertDoesNotThrow(()->rep.save(supplier));
        supplier.setName("prova Update");
        assertDoesNotThrow(()->rep.save(supplier));

    }

    @Test
    void notThrowExceptionOnDelete() throws ConnectionException, ClientExcpetion {
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);

        JdbcSupplierRepository rep = new JdbcSupplierRepository(exec.connection);
        Supplier supplier = new Supplier(0,"Lenovo","Calle Santa Hortensia 26, 28002, Madrid");
        assertDoesNotThrow(()->rep.save(supplier));

        assertDoesNotThrow(()->rep.delete(supplier));

    }

    @Test
    void ThrowProductException() throws ConnectionException{
        ConnectionProperties conn = new ConnectionProperties("jdbc:mysql://localhost:3306/iserm","root","");
        XmlSchemaLoader schema = new XmlSchemaLoader("src/test/resources/xml/schema.xml");

        assertDoesNotThrow(()->new ExecuteCommands(schema,conn));
        ExecuteCommands exec = new ExecuteCommands(schema,conn);


        JdbcSupplierRepository rep = new JdbcSupplierRepository(exec.connection);
        var supplier = new Supplier(-1,"Test Error name","testError Address");

        assertThrows(SupplierException.class,()->rep.save(supplier));

        assertNotEquals(0,supplier.getId());
    }
}
