package cat.uvic.teknos.m06.iserm.domain.Repository;

import cat.uvic.teknos.m06.iserm.domain.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.iserm.domain.Exception.ProductException;
import cat.uvic.teknos.m06.iserm.domain.model.Client;
import cat.uvic.teknos.m06.iserm.domain.model.Repository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Aggregates.set;
import static com.mongodb.client.model.Updates.combine;

public class MongoClientRepository implements Repository<Client> {
    private final MongoDatabase db;
    private final MongoCollection<Client> clients;

    public MongoClientRepository(MongoDatabase database) {
        this.db = database;
        this.clients = this.db.getCollection("clients",Client.class);
    }

    @Override
    public void save(Client client) throws Exception {
        if(client.getId()==0){
            insert(client);
        }else{
            update(client);
        }


    }

    @Override
    public void delete(Client client) throws ProductException, ClientExcpetion {
        Bson filter = Filters.eq("id", client.getId());
        var res = clients.deleteOne(filter);
        System.out.println("deleted: " + res);
    }

    @Override
    public Client getById(int id) throws ProductException, ClientExcpetion {
        Client cli = new Client();
        Bson filter = Filters.eq("id",id);
        var res = clients.find(filter);

        var itr = res.iterator();

        while(itr.hasNext()) {
            cli = itr.next();
        }

        return cli;
    }

    @Override
    public List<Client> getAll() throws ClientExcpetion, ProductException {
        List<Client> list = new ArrayList<>();
        var result = clients.find();
        var itr = result.iterator();

        while(itr.hasNext()){
            list.add(itr.next());
        }
        return list;
    }

    @Override
    public void insert(Client client) throws Exception {
        var res = this.clients.insertOne(client);

        if(res.getInsertedId()==null){
            throw new ClientExcpetion("cannot insert client");
        }
        System.out.println("insert: "+ res);
    }

    @Override
    public void update(Client client) throws ClientExcpetion {
        var filter = Filters.eq("id",client.getId());
        var update = combine(Updates.set("name",client.getName()),Updates.set("surname",client.getSurname()),
                                    Updates.set("address",client.getAddress()));

        var res = this.clients.updateOne(filter,update);

        if(res.getModifiedCount()==0){
            throw new ClientExcpetion("cannot update client");
        }
    }
}
