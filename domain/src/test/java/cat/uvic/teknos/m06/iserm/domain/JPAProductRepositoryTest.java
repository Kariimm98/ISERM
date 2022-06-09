package cat.uvic.teknos.m06.iserm.domain;

import cat.uvic.teknos.m06.iserm.domain.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.iserm.domain.Exception.ProductException;
import cat.uvic.teknos.m06.iserm.domain.Repository.JPAClientRepository;
import cat.uvic.teknos.m06.iserm.domain.Repository.JPAProductRepository;
import cat.uvic.teknos.m06.iserm.domain.Repository.JPASupplierRepository;
import cat.uvic.teknos.m06.iserm.domain.model.Product;
import cat.uvic.teknos.m06.iserm.domain.model.Supplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JPAProductRepositoryTest {
    private static EntityManagerFactory entity ;
    private static JPAClientRepository repository;

    @BeforeAll
    static void setUp(){
        entity = Persistence.createEntityManagerFactory("ISERM");
        repository = new JPAClientRepository(entity);
    }

    @Test
    public void saveInsert() throws Exception {
        var repos = new JPAProductRepository(entity);
        var reposSupp = new JPASupplierRepository(entity);
        var supp = new Supplier();

        supp = reposSupp.getById(1);

        Product product = new Product();

        product.setName("BenQ");
        product.setDescription("pantalla BenQ 22");
        product.setPrice((float) 239.99);
        product.addSupplier(supp);

        assertDoesNotThrow(()->{
            repos.save(product);
        });

        assertTrue(product.getId()>0);

    }

    @Test
    public void saveUpdate() throws ClientExcpetion, ProductException {
        var repos = new JPAProductRepository(entity);
        var reposSupp = new JPASupplierRepository(entity);

        var prod = repos.getAll();
        var supp = reposSupp.getAll();

        for(var p : prod) {
            for (var s : supp) {
                p.addSupplier(s);
            }
            assertDoesNotThrow(()->{
                repos.save(p);
            });
        }
    }

    @Test
    void FindById() throws Exception {

        var product = repository.getById(1);
        assertTrue(product.getId()>0);
    }

    @Test
    void selectAll() throws ClientExcpetion, ProductException {
        var list = repository.getAll();
        assertNotNull(list);
    }

    @Test
    void deleteOne() throws ClientExcpetion, ProductException {
        var client = repository.getById(2);
        repository.delete(client);
        client = repository.getById(2);
        assertNull(client);

    }
}
