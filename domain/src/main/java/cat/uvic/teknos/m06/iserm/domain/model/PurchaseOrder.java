package cat.uvic.teknos.m06.iserm.domain.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Client client;

    private Date purchased_on;

    public PurchaseOrder(){
    }

    public PurchaseOrder(int id, Date data) {
        this.id = id;
        this.purchased_on = data;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getData() {
        return purchased_on;
    }

    public void setData(Date data) {
        this.purchased_on = data;
    }

}