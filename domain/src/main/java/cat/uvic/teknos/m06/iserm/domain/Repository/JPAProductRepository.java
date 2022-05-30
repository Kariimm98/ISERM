package cat.uvic.teknos.m06.iserm.domain.Repository;

import cat.uvic.teknos.m06.iserm.domain.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.iserm.domain.Exception.ProductException;
import cat.uvic.teknos.m06.iserm.domain.model.Product;
import cat.uvic.teknos.m06.iserm.domain.model.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JPAProductRepository implements Repository<Product> {
    private final EntityManagerFactory entity;

    public JPAProductRepository(EntityManagerFactory entity) {
        this.entity = entity;
    }

    @Override
    public void save(Product product) throws Exception {
        if(product.getId() == 0){
            insert(product);
        }else{
            update(product);
        }
    }

    @Override
    public void delete(Product product) throws ProductException {
        var entityManager = entity.createEntityManager();
        entityManager.getTransaction().begin();

        if(product!=null){
            var merged = entityManager.merge(product);
            entityManager.remove(merged);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public Product getById(int id) throws ProductException, ClientExcpetion {
        var entityManager = entity.createEntityManager();

        return entityManager.find(Product.class,id);
    }

    @Override
    public List<Product> getAll() throws ClientExcpetion, ProductException {
        var entityManager = entity.createEntityManager();

        return entityManager.createNativeQuery("SELECT * FROM product").getResultList();
    }

    @Override
    public void insert(Product product) throws Exception {
        var entityManager = entity.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Product product) throws Exception {
        var entityManager = entity.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();
    }
}
