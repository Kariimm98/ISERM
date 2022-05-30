package cat.uvic.teknos.m06.iserm.domain.model;

import javax.persistence.*;

@Entity
public class PurchaseLineOrder {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Transient
    private int idOrder;
    @Transient
    private int idProduct;
    private float quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}