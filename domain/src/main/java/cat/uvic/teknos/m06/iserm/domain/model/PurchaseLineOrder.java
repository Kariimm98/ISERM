package cat.uvic.teknos.m06.iserm.domain.model;

import javax.persistence.*;

@Entity
public class PurchaseLineOrder {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="purchase_order_id", nullable=false)
    private PurchaseOrder order;
    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
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

    public void setOrder(PurchaseOrder order) {
        this.order = order;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}