package cat.uvic.teknos.m06.connectorSQL.list.Repository;


import cat.uvic.teknos.m06.connectorSQL.list.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.connectorSQL.list.Exception.ProductException;
import cat.uvic.teknos.m06.connectorSQL.list.model.Client;
import cat.uvic.teknos.m06.connectorSQL.list.model.Repository;

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
        }
    }

    @Override
    public void delete(Client client) throws ProductException, ClientExcpetion {

    }

    @Override
    public Client getById(int id) throws ProductException, ClientExcpetion {
        return null;
    }

    @Override
    public List<Client> getAll() throws ClientExcpetion, ProductException {
        return null;
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

    }
}
