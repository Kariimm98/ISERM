package cat.uvic.teknos.m06.iserm.domain.Repository;


import cat.uvic.teknos.m06.iserm.domain.Exception.ProductException;
import cat.uvic.teknos.m06.iserm.domain.model.PurchaseLineOrder;
import cat.uvic.teknos.m06.iserm.domain.model.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JPAPurchaseLineOrderRepository implements Repository<PurchaseLineOrder> {
    private final EntityManagerFactory entity;

    public JPAPurchaseLineOrderRepository(EntityManagerFactory entity) {
        this.entity = entity;
    }

    @Override
    public void save(PurchaseLineOrder line) throws Exception {
        if(line.getId() == 0){
            insert(line);
        }else{
            update(line);
        }
    }

    @Override
    public void delete(PurchaseLineOrder line) throws ProductException {
        var entityManager = entity.createEntityManager();
        entityManager.getTransaction().begin();

        if(line!=null){
            var merged = entityManager.merge(line);
            entityManager.remove(merged);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public PurchaseLineOrder getById(int id) throws ProductException {
        var entityManager = entity.createEntityManager();
        return entityManager.find(PurchaseLineOrder.class,id);
    }

    @Override
    public List<PurchaseLineOrder> getAll() throws ProductException {
        var entityManager = entity.createEntityManager();

        return entityManager.createNativeQuery("SELECT * FROM PurchaseLineOrder").getResultList();
    }

    @Override
    public void insert(PurchaseLineOrder line) throws Exception {
        var entityManager = entity.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(line);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(PurchaseLineOrder line) throws Exception {
        var entityManager = entity.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(line);
        entityManager.getTransaction().commit();
    }
}
