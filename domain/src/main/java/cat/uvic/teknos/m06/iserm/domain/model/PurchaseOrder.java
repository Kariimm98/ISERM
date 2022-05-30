package cat.uvic.teknos.m06.iserm.domain.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Transient
    private int idClient;
    private Date purchased_on;
    @Transient
    private List<PurchaseLineOrder> lines;

    public PurchaseOrder(){

    }

    public PurchaseOrder(int id, int idClient, Date data, List<PurchaseLineOrder> lines) {
        this.id = id;
        this.idClient = idClient;
        this.purchased_on = data;
        this.lines = lines;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return null;
    }

    public void setClient(Client client) {
        this.idClient = client.getId();
    }

    public Date getData() {
        return purchased_on;
    }

    public void setData(Date data) {
        this.purchased_on = data;
    }

    public List<PurchaseLineOrder> getLines() {
        return lines;
    }

    public void setLines(List<PurchaseLineOrder> lines) {
        this.lines = lines;
    }
}