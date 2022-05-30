package cat.uvic.teknos.m06.iserm.domain;

import cat.uvic.teknos.m06.iserm.domain.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.iserm.domain.Exception.ProductException;
import cat.uvic.teknos.m06.iserm.domain.Repository.JPAProductRepository;
import cat.uvic.teknos.m06.iserm.domain.Repository.JPASupplierRepoistory;
import cat.uvic.teknos.m06.iserm.domain.model.Product;
import cat.uvic.teknos.m06.iserm.domain.model.Supplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

public class JPASupplierRepositoryTest {
    private static EntityManagerFactory entity ;
    private static JPASupplierRepoistory repository;

    @BeforeAll
    static void setUp(){
        entity = Persistence.createEntityManagerFactory("ISERM");
        repository = new JPASupplierRepoistory(entity);
    }

    @Test
    public void saveInsert() throws Exception {
        var repos = new JPASupplierRepoistory(entity);
        var sup = new Supplier();
        sup.setName("HP");
        sup.setAddress("Av.Las Vegas");

        assertDoesNotThrow(()->{
            repos.save(sup);
        });

        assertTrue(sup.getId()>0);

    }

    @Test
    public void saveUpdate(){
        var repos = new JPASupplierRepoistory(entity);
        var sup = new Supplier();
        sup.setName("HP");
        sup.setAddress("Av.Las Vegas");

        assertDoesNotThrow(()->{
            repos.save(sup);
        });

        assertTrue(sup.getId()>0);
    }

    @Test
    void FindById() throws Exception {

        var sup = repository.getById(1);
        assertTrue(sup.getId()>0);
    }

    @Test
    void selectAll() throws ClientExcpetion, ProductException {
        var list = repository.getAll();
        assertNotNull(list);
    }

    @Test
    void deleteOne() throws ClientExcpetion, ProductException {
        var sup = repository.getById(2);
        repository.delete(sup);
        sup = repository.getById(2);
        assertNull(sup);

    }
}
