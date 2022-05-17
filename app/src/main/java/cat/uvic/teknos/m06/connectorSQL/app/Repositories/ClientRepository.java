package cat.uvic.teknos.m06.connectorSQL.app.Repositories;

import cat.uvic.teknos.m06.connectorSQL.app.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.connectorSQL.app.Model.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClientRepository implements Repository<Client>{
    private  Connection conn;
    private  String insert = "INSERT INTO client SET name = ?, surname = ?, address = ? ";

    public ClientRepository(Connection conn){
        this.conn = conn;
    }

    @Override
    public void save(Client client) throws ClientExcpetion {
        try{
            if(client.getId() == 0) {
                var stat = conn.prepareStatement(insert);

                stat.setString(1, client.getName());
                stat.setString(2, client.getSurname());
                stat.setString(3, client.getAddress());

                stat.executeUpdate();

                var res = stat.();
                if(stat.next()){
                    client.setId(res.getInt("id"));
                }

            }

        }catch(SQLException e){
            throw new ClientExcpetion("could not insert client: "+ client.getName() + " " + client.getSurname());
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
