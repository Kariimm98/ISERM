package cat.uvic.teknos.m06.iserm.domain;

import cat.uvic.teknos.m06.iserm.domain.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.iserm.domain.Exception.ProductException;
import cat.uvic.teknos.m06.iserm.domain.Repository.JPAClientRepository;
import cat.uvic.teknos.m06.iserm.domain.Repository.JPAProductRepository;
import cat.uvic.teknos.m06.iserm.domain.Repository.JPAPurchaseLineOrderRepository;
import cat.uvic.teknos.m06.iserm.domain.Repository.JPAPurchaseOrderRepository;
import cat.uvic.teknos.m06.iserm.domain.model.Client;
import cat.uvic.teknos.m06.iserm.domain.model.Product;
import cat.uvic.teknos.m06.iserm.domain.model.PurchaseLineOrder;
import cat.uvic.teknos.m06.iserm.domain.model.PurchaseOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JPAPurchaseOrderRepositoryTest {
    private static EntityManagerFactory entity ;
    private static JPAPurchaseOrderRepository repository;

    @BeforeAll
    static void setUp(){
        entity = Persistence.createEntityManagerFactory("ISERM");
        repository = new JPAPurchaseOrderRepository(entity);
    }

    @Test
    public void saveInsert() throws Exception {
        var repos = new JPAPurchaseOrderRepository(entity);
        var entityClient = new JPAClientRepository(entity);
        var reposLine = new JPAPurchaseLineOrderRepository(entity);
        var reposProduct = new JPAProductRepository(entity);

        //searching Client of order
        var client = entityClient.getById(1);

        var order = new PurchaseOrder();
        order.setClient(client);
        repository.save(order);
        assertTrue(order.getId()> 0);


        //insert first line to order
        var product = reposProduct.getById(1);
        var line = new PurchaseLineOrder();
        line.setOrder(order);
        line.setProduct(product);
        line.setQuantity(2);

        reposLine.save(line);

        assertTrue(line.getId()> 0);
        //insert second line to order
        var product2 = reposProduct.getById(2);
        var line2 = new PurchaseLineOrder();
        line2.setOrder(order);
        line2.setProduct(product2);
        line2.setQuantity(4);

        reposLine.save(line2);
        assertTrue(line2.getId()> 0);

    }
}
