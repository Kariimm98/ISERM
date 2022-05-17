package cat.uvic.teknos.m06.connectorSQL.app.Model;

import java.util.Date;
import java.util.List;

public class PurchaseOrder {
    private int id;
    private Client client;
    private Date purchased_on;
    private List<PurchaseLineOrder> lines;

    public PurchaseOrder(int id, Client client, Date data, List<PurchaseLineOrder> lines) {
        this.id = id;
        this.client = client;
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
        return client;
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

    public void setLines(List<PurchaseLineOrder> lines) {
        this.lines = lines;
    }
}
