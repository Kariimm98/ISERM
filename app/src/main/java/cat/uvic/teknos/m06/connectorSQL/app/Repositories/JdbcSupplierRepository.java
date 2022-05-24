package cat.uvic.teknos.m06.connectorSQL.app.Repositories;

import cat.uvic.teknos.m06.connectorSQL.app.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.connectorSQL.app.Exception.ProductException;
import cat.uvic.teknos.m06.connectorSQL.app.Exception.SupplierException;
import cat.uvic.teknos.m06.connectorSQL.app.Model.Client;
import cat.uvic.teknos.m06.connectorSQL.app.Model.Supplier;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcSupplierRepository implements Repository<Supplier>{
    private Connection conn;
    private String insert = "INSERT INTO supplier SET name = ?,address = ? ";
    private String update = "UPDATE supplier SET name = ?, address = ? where id = ?";
    private String delete = "DELETE FROM supplier WHERE id = ? ";
    private String select = "SELECT * FROM supplier WHERE id = ?";
    private String selectAll = "SELECT * FROM supplier";

    public JdbcSupplierRepository(Connection conn){
        this.conn = conn;
    }

    @Override
    public void save(Supplier supplier) throws Exception {
        if(supplier.getId() == 0) {
            insert(supplier);
        }else{
            update(supplier);
        }
    }

    @Override
    public void delete(Supplier supplier) throws ClientExcpetion {
        try{
            var stat = conn.prepareStatement(delete,Statement.CLOSE_CURRENT_RESULT);

            stat.setInt(1,supplier.getId());
            var res = stat.executeUpdate();
            if(res==0){
                throw new ProductException("could not supplier product");
            }

        }catch(Exception e){
            throw new ClientExcpetion("could not supplier product: "+ supplier.getName());
        }
    }

    @Override
    public Supplier getById(int id) throws ClientExcpetion {
        Supplier supp = null;
        try {
            var stat = conn.prepareStatement(select);

            stat.setInt(1,id);

            var res = stat.executeQuery();

            if (res.next()) {
                supp = new Supplier(res.getInt("id"), res.getString("name"), res.getString("address"));
            }
        }catch(Exception e){
            throw new ClientExcpetion("could not get Supplier by id");
        }
        return supp;
    }

    @Override
    public List<Supplier> getAll() throws ClientExcpetion {
        Supplier supp = null;
        List<Supplier> list = new ArrayList<Supplier>();
        try {
            var stat = conn.prepareStatement(selectAll);

            var res = stat.executeQuery();

            while(res.next()) {
                supp = new Supplier(res.getInt("id"), res.getString("name"), res.getString("address"));
                list.add(supp);
            }
        }catch(Exception e){
            throw new ClientExcpetion("could not get Supplier by id");
        }
        return list;
    }

    @Override
    public void insert(Supplier supplier) throws SupplierException{
        try{
            var stat = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            stat.setString(1, supplier.getName());
            stat.setString(2, supplier.getAddress());

            stat.executeUpdate();

            var res = stat.getGeneratedKeys();

            if(res == null){
                throw new ClientExcpetion("could not insert client: "+ supplier.getName());
            }

            if(res.next()){
                supplier.setId(res.getInt(1));
            }
        }catch(Exception e){
            throw new SupplierException("could not insert client: "+ supplier.getName());
        }
    }

    @Override
    public void update(Supplier supplier) throws SupplierException{
        try{
            var stat = conn.prepareStatement(update);

            //SET
            stat.setString(1, supplier.getName());
            stat.setString(2, supplier.getAddress());

            //WHERE
            stat.setInt(3,supplier.getId());

            var res = stat.executeUpdate();

            if(res != 1){
                throw new ClientExcpetion("could not updated supplier: "+ supplier.getId());
            }

        }catch(Exception e){
            throw new SupplierException("could not updated supplier: "+ supplier.getName());
        }
    }
}
