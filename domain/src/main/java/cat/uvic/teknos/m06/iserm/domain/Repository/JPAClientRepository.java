package cat.uvic.teknos.m06.iserm.domain.Repository;


import cat.uvic.teknos.m06.iserm.domain.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.iserm.domain.Exception.ProductException;
import cat.uvic.teknos.m06.iserm.domain.model.Client;
import cat.uvic.teknos.m06.iserm.domain.model.Product;
import cat.uvic.teknos.m06.iserm.domain.model.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JPAClientRepository implements Repository<Client> {

    private final EntityManagerFactory entity;

    public JPAClientRepository(EntityManagerFactory entity){
        this.entity = entity;
    }

    @Override
    public void save(Client client) throws Exception {
        if(client.getId() == 0){
            insert(client);
        }else{
            update(client);
        }
    }

    @Override
    public void delete(Client client) throws ProductException, ClientExcpetion {
        var entityManager = entity.createEntityManager();
        entityManager.getTransaction().begin();

        if(client!=null){
            var merged = entityManager.merge(client);
            entityManager.remove(merged);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public Client getById(int id) throws ProductException, ClientExcpetion {
        var entityManager = entity.createEntityManager();
        return entityManager.find(Client.class,id);
    }

    @Override
    public List<Client> getAll() throws ClientExcpetion, ProductException {
        var entityManager = entity.createEntityManager();

        return entityManager.createNativeQuery("SELECT * FROM client").getResultList();
    }

    @Override
    public void insert(Client client) throws Exception {
        var entityManager = entity.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Client client) throws Exception {
        var entityManager = entity.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(client);
        entityManager.getTransaction().commit();
    }
}