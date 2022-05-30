package cat.uvic.teknos.m06.iserm.domain;

import cat.uvic.teknos.m06.iserm.domain.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.iserm.domain.Exception.ProductException;
import cat.uvic.teknos.m06.iserm.domain.Repository.JPAClientRepository;
import cat.uvic.teknos.m06.iserm.domain.Repository.JPAProductRepository;
import cat.uvic.teknos.m06.iserm.domain.model.Client;
import cat.uvic.teknos.m06.iserm.domain.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

public class JPAProductRepositoryTest {
    private static EntityManagerFactory entity ;
    private static JPAProductRepository repository;

    @BeforeAll
    static void setUp(){
        entity = Persistence.createEntityManagerFactory("ISERM");
        repository = new JPAProductRepository(entity);
    }

    @Test
    public void saveInsert() throws Exception {
        var repos = new JPAProductRepository(entity);
        var prod = new Product();
        prod.setName("Razer Viper Ultimate");
        prod.setDescription("Raton Gaming Inalambrico 20000 DPI");
        prod.setPrice((float)81.74);

        assertDoesNotThrow(()->{
            repos.save(prod);
        });

        assertTrue(prod.getId()>0);

    }

    @Test
    public void saveUpdate(){
        var repos = new JPAProductRepository(entity);
        var prod = new Product();
        prod.setName("Razer Viper Ultimate");
        prod.setDescription("Ratón Gaming Inalámbrico 20000 DPI");
        prod.setPrice((float)81.74);

        assertDoesNotThrow(()->{
            repos.save(prod);
        });

        assertTrue(prod.getId()>0);
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
        var product = repository.getById(2);
        repository.delete(product);
        product = repository.getById(2);
        assertNull(product);

    }
}
