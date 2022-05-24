package cat.uvic.teknos.m06.connectorSQL.app.Repositories;

import cat.uvic.teknos.m06.connectorSQL.app.Exception.ProductException;
import cat.uvic.teknos.m06.connectorSQL.app.Model.Product;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductRepository implements Repository<Product>{

    private Connection conn;
    private  String insert = "INSERT INTO product SET name = ?, description = ?, price = ? ";
    private String update = "UPDATE product SET name = ?, description = ?, price = ? where id = ?";
    private String delete =  "DELETE FROM product WHERE id = ?";
    private String selectById = "SELECT * FROM product WHERE id = ?";
    private String selectAll = "SELECT * FROM product";


    public JdbcProductRepository(Connection conn){
        this.conn = conn;
    }

    @Override
    public void save(Product product) throws ProductException {
        if(product.getId() == 0) {
            insert(product);
        }else{
            update(product);
        }
    }

    @Override
    public void delete(Product product) throws ProductException {
        try{
            var stat = conn.prepareStatement(delete,Statement.CLOSE_CURRENT_RESULT);

            stat.setInt(1,product.getId());
            var res = stat.executeUpdate();
            if(res==0){
                throw new ProductException("could not delete product");
            }

        }catch(Exception e){
            throw new ProductException("could not delete product: "+ product.getName());
        }
    }

    @Override
    public Product getById(int id) throws ProductException {
        Product prod = null;
        try {
            var stat = conn.prepareStatement(selectById);

            stat.setInt(1,id);
            var res = stat.executeQuery();

            if (res.next()) {
                prod = new Product(res.getInt("id"), res.getString("name"), res.getString("description"), res.getFloat("price"));
            }
        }catch(Exception e){
            throw new ProductException("could not get product by id");
        }
        return prod;
    }

    @Override
    public List<Product> getAll() throws ProductException {
        Product prod = null;
        List<Product> list = new ArrayList<Product>();
        try {
            var stat = conn.prepareStatement(selectAll);
            var res = stat.executeQuery();

            while(res.next()) {
                prod = new Product(res.getInt("id"), res.getString("name"), res.getString("description"), res.getFloat("price"));
                list.add(prod);
            }
        }catch(Exception e){
            throw new ProductException("could not get product by id");
        }
        return list;
    }

    @Override
    public void insert(Product product) throws ProductException {
        try{
            var stat = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            stat.setString(1, product.getName());
            stat.setString(2, product.getDescription());
            stat.setFloat(3, product.getPrice());

            stat.executeUpdate();

            var res = stat.getGeneratedKeys();

            if(res == null){
                throw new ProductException("could not insert product: "+ product.getName());
            }

            if(res.next()){
                product.setId(res.getInt(1));
            }

        }catch(Exception e){
            throw new ProductException("could not insert product: "+ product.getName());
        }
    }

    @Override
    public void update(Product product) throws ProductException {
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
                throw new ProductException("could not updated product: "+ product.getId()+" " + product.getName());
            }

        }catch(Exception e){
            throw new ProductException("could not updated product: "+ product.getName());
        }
    }
}
