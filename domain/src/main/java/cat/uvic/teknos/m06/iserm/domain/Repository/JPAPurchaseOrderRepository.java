package cat.uvic.teknos.m06.iserm.domain.Repository;

import cat.uvic.teknos.m06.iserm.domain.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.iserm.domain.Exception.ProductException;
import cat.uvic.teknos.m06.iserm.domain.model.PurchaseOrder;
import cat.uvic.teknos.m06.iserm.domain.model.Repository;
import cat.uvic.teknos.m06.iserm.domain.model.Supplier;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JPAPurchaseOrderRepository implements Repository<PurchaseOrder> {

    private final EntityManagerFactory entity;

    public JPAPurchaseOrderRepository(EntityManagerFactory entity){
        this.entity = entity;
    }
    @Override
    public void save(PurchaseOrder order) throws Exception {
        if(order.getId() == 0){
            insert(order);
        }else{
            update(order);
        }
    }

    @Override
    public void delete(PurchaseOrder order) throws ProductException {
        var entityManager = entity.createEntityManager();
        entityManager.getTransaction().begin();

        if(order!=null){
            var merged = entityManager.merge(order);
            entityManager.remove(merged);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public PurchaseOrder getById(int id) throws ProductException, ClientExcpetion {
        var entityManager = entity.createEntityManager();
        return entityManager.find(PurchaseOrder.class,id);
    }

    @Override
    public List<PurchaseOrder> getAll() throws ClientExcpetion, ProductException {
        var entityManager = entity.createEntityManager();
        return entityManager.createNativeQuery("SELECT * FROM supplier").getResultList();
    }

    @Override
    public void insert(PurchaseOrder order) throws Exception {
        var entityManager = entity.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(PurchaseOrder order) throws Exception {
        var entityManager = entity.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(order);
        entityManager.getTransaction().commit();
    }
}
