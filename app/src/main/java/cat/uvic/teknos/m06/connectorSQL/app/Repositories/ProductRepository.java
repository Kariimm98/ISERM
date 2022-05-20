package cat.uvic.teknos.m06.connectorSQL.app.Repositories;

import cat.uvic.teknos.m06.connectorSQL.app.Exception.ClientExcpetion;
import cat.uvic.teknos.m06.connectorSQL.app.Exception.ProductException;
import cat.uvic.teknos.m06.connectorSQL.app.Model.Product;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class ProductRepository implements Repository<Product>{

    private Connection conn;
    private  String insert = "INSERT INTO product SET name = ?, description = ?, price = ? ";
    private String update = "UPDATE product SET name = ?, description = ?, price = ? where id = ?";


    public ProductRepository(Connection conn){
        this.conn = conn;
    }

    @Override
    public void save(Product product) throws ProductException {
        if(product.getId() == 0) {
            insertProduct(product);
        }else{
            updateProduct(product);
        }
    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public Product getById(int id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    private void insertProduct(Product product) throws ProductException {
        try{
            var stat = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            stat.setString(1, product.getName());
            stat.setString(2, product.getDescription());
            stat.setFloat(3, product.getPrice());

            stat.executeUpdate();

            var res = stat.getGeneratedKeys();

            if(res == null){
                throw new ProductException("could not insert client: "+ product.getName());
            }

            if(res.next()){
                product.setId(res.getInt(1));
            }
        }catch(Exception e){
            throw new ProductException("could not insert client: "+ product.getName());
        }
    }

    private void updateProduct(Product product) throws ProductException {
        try{
            var stat = conn.prepareStatement(update);

            //SET
            stat.setString(1, product.getName());
            stat.setString(2, product.getDescription());
            stat.setFloat(3, product.getPrice());

            //WHERE
            stat.setInt(4,product.getId());

            var res = stat.executeUpdate();

            if(res != 1){
                throw new ProductException("could not updated client: "+ product.getId()+" " + product.getName());
            }

        }catch(Exception e){
            throw new ProductException("could not updated client: "+ product.getName());
        }
    }


}
