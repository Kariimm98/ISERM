package cat.uvic.teknos.m06.connectorSQL.app.Repositories;

import cat.uvic.teknos.m06.connectorSQL.app.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.connectorSQL.app.Exception.ProductException;
import cat.uvic.teknos.m06.connectorSQL.app.Model.Client;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcClientRepository implements Repository<Client>{
    private Connection conn;
    private String insert = "INSERT INTO client SET name = ?, surname = ?, address = ? ";
    private String update = "UPDATE client SET name = ?, surname = ?, address = ? where id = ?";
    private String delete = "DELETE FROM client WHERE id = ?";
    private String select = "SELECT * FROM client WHERE id = ?";
    private String selectAll = "SELECT * FROM client";


    public JdbcClientRepository(Connection conn){
        this.conn = conn;
    }

    @Override
    public void save(Client client) throws ClientExcpetion {

        if(client.getId() == 0) {
            insert(client);
        }else{
            update(client);
        }
    }

    @Override
    public void delete(Client client) throws ClientExcpetion {
        try{
            var stat = conn.prepareStatement(delete,Statement.CLOSE_CURRENT_RESULT);

            stat.setInt(1,client.getId());
            var res = stat.executeUpdate();
            if(res==0){
                throw new ProductException("could not client product");
            }

        }catch(Exception e){
            throw new ClientExcpetion("could not client product: "+ client.getName());
        }
    }

    @Override
    public Client getById(int id) throws ClientExcpetion {
        Client cli = null;
        try {
            var stat = conn.prepareStatement(select);

            stat.setInt(1,id);

            var res = stat.executeQuery();

            if (res.next()) {
                cli = new Client(res.getInt("id"), res.getString("name"), res.getString("surname"), res.getString("address"));
            }
        }catch(Exception e){
            throw new ClientExcpetion("could not get client by id");
        }
        return cli;
    }

    @Override
    public List<Client> getAll() throws ClientExcpetion {
        Client cli = null;
        List<Client> list = new ArrayList<Client>();

        try {
            var stat = conn.prepareStatement(selectAll);

            var res = stat.executeQuery();

            while(res.next()) {
                cli = new Client(res.getInt("id"), res.getString("name"), res.getString("surname"), res.getString("address"));
                list.add(cli);
            }

        }catch(Exception e){
            throw new ClientExcpetion("could not get client by id");
        }

        return list;
    }

    @Override
    public void insert(Client client) throws ClientExcpetion{
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

    @Override
    public void update(Client client) throws ClientExcpetion{

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
}
