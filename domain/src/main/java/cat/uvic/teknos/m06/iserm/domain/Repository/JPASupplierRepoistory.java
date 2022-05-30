package cat.uvic.teknos.m06.iserm.domain.Repository;

import cat.uvic.teknos.m06.iserm.domain.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.iserm.domain.Exception.ProductException;
import cat.uvic.teknos.m06.iserm.domain.model.Product;
import cat.uvic.teknos.m06.iserm.domain.model.Repository;
import cat.uvic.teknos.m06.iserm.domain.model.Supplier;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JPASupplierRepoistory implements Repository<Supplier> {
    private final EntityManagerFactory entity;

    public JPASupplierRepoistory(EntityManagerFactory entity) {
        this.entity = entity;
    }

    @Override
    public void save(Supplier supplier) throws Exception {
        if(supplier.getId() == 0){
            insert(supplier);
        }else{
            update(supplier);
        }
    }

    @Override
    public void delete(Supplier supplier) throws ProductException {
        var entityManager = entity.createEntityManager();
        entityManager.getTransaction().begin();

        if(supplier!=null){
            var merged = entityManager.merge(supplier);
            entityManager.remove(merged);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public Supplier getById(int id) throws ProductException, ClientExcpetion {
        var entityManager = entity.createEntityManager();
        return entityManager.find(Supplier.class,id);
    }

    @Override
    public List<Supplier> getAll() throws ClientExcpetion, ProductException {
        var entityManager = entity.createEntityManager();
        return entityManager.createNativeQuery("SELECT * FROM supplier").getResultList();
    }

    @Override
    public void insert(Supplier supplier) throws Exception {
        var entityManager = entity.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(supplier);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Supplier supplier) throws Exception {
        var entityManager = entity.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(supplier);
        entityManager.getTransaction().commit();
    }
}
