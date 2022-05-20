package cat.uvic.teknos.m06.connectorSQL.app.Repositories;

import cat.uvic.teknos.m06.connectorSQL.app.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.connectorSQL.app.Exception.SupplierException;
import cat.uvic.teknos.m06.connectorSQL.app.Model.Supplier;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class SupplierRepository implements Repository<Supplier>{
    private Connection conn;
    private String insert = "INSERT INTO supplier SET name = ?, surname = ?, address = ? ";
    private String update = "UPDATE supplier SET name = ?, surname = ?, address = ? where id = ?";
    private String delete = "DELETE FROM supplier WHERE id = ?";

    public SupplierRepository(Connection conn){
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
    public void delete(Supplier supplier) {

    }

    @Override
    public Supplier getById(int id) {
        return null;
    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }

    @Override
    public void insert(Supplier supplier) throws SupplierException{
        try{
            var stat = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            stat.setString(1, supplier.getName());
            stat.setString(3, supplier.getAddress());

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
            stat.setString(3, supplier.getAddress());

            //WHERE
            stat.setInt(4,supplier.getId());

            var res = stat.executeUpdate();

            if(res != 1){
                throw new ClientExcpetion("could not updated client: "+ supplier.getId());
            }

        }catch(Exception e){
            throw new SupplierException("could not updated client: "+ supplier.getName());
        }
    }
}
