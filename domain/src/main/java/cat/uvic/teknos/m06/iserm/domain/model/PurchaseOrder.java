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
    @Transient
    private List<PurchaseLineOrder> lines;

    public PurchaseOrder(){
    }

    public PurchaseOrder(int id, Date data, List<PurchaseLineOrder> lines) {
        this.id = id;
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

    public List<PurchaseLineOrder> getLines() {
        return lines;
    }

    public void addLines(List<PurchaseLineOrder> lines) {
        this.lines = lines;
    }

    public void addLine(PurchaseLineOrder line ){this.lines.add(line);}
}