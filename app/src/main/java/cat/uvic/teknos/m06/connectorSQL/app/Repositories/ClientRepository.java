package cat.uvic.teknos.m06.connectorSQL.app.Repositories;

import cat.uvic.teknos.m06.connectorSQL.app.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.connectorSQL.app.Model.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ClientRepository implements Repository<Client>{
    private  Connection conn;
    private  String insert = "INSERT INTO client SET name = ?, surname = ?, address = ? ";
    private String update = "UPDATE client SET name = ?, surname = ?, address = ? where id = ?";


    public ClientRepository(Connection conn){
        this.conn = conn;
    }

    @Override
    public void save(Client client) throws ClientExcpetion {

        if(client.getId() == 0) {
            insertClient(client);
        }else{
            updateClient(client);
        }

    }

    private void insertClient(Client client) throws ClientExcpetion{
        try{
            var stat = conn.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);

            stat.setString(1, client.getName());
            stat.setString(2, client.getSurname());
            stat.setString(3, client.getAddress());

            stat.executeUpdate();

            var res = stat.getGeneratedKeys();

            if(res == null){
                throw new ClientExcpetion("could not insert client: "+ client.getName() + " " + client.getSurname());
            }

            if(res.next()){
                client.setId(res.getInt(1));
            }
        }catch(Exception e){
            throw new ClientExcpetion("could not insert client: "+ client.getName() + " " + client.getSurname());
        }
    }

    private void updateClient(Client client) throws ClientExcpetion{

        try{
            var stat = conn.prepareStatement(update);

            //SET
            stat.setString(1, client.getName());
            stat.setString(2, client.getSurname());
            stat.setString(3, client.getAddress());

            //WHERE
            stat.setInt(4,client.getId());

            var res = stat.executeUpdate();

            if(res != 1){
                throw new ClientExcpetion("could not updated client: "+ client.getId()+" " + client.getName() + " " + client.getSurname());
            }

        }catch(Exception e){
            throw new ClientExcpetion("could not updated client: "+ client.getName() + " " + client.getSurname());
        }
    }

    @Override
    public void delete(Client client) {

    }

    @Override
    public Client getById(int id) {
        return null;
    }

    @Override
    public List<Client> getAll() {
        return null;
    }
}
