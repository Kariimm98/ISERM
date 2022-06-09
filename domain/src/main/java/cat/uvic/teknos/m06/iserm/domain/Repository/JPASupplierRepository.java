package cat.uvic.teknos.m06.iserm.domain.Repository;

import cat.uvic.teknos.m06.iserm.domain.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.iserm.domain.Exception.ProductException;
import cat.uvic.teknos.m06.iserm.domain.model.Repository;
import cat.uvic.teknos.m06.iserm.domain.model.Supplier;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class JPASupplierRepository implements Repository<Supplier> {
    private final EntityManagerFactory entity;

    public JPASupplierRepository(EntityManagerFactory entity) {
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
        List<Object[]> table = entityManager.createNativeQuery("SELECT * FROM supplier").getResultList();
        List<Supplier> list = new ArrayList<Supplier>();

        for(Object[] value : table){
            Supplier supp = new Supplier((Integer) value[0], (String) value[1], (String) value[2]);
            list.add(supp);
        }
        return list;
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
