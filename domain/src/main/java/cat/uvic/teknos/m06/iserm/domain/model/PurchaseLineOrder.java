package cat.uvic.teknos.m06.iserm.domain.model;

import javax.persistence.*;

@Entity
public class PurchaseLineOrder {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Transient
    private PurchaseOrder order;
    @Transient
    private Product product;
    private float quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PurchaseOrder getOrder() {
        return this.order;
    }

    public void setIdOrder(PurchaseOrder order) {
        this.order = order;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setIdProduct(Product product) {
        this.product = product;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}