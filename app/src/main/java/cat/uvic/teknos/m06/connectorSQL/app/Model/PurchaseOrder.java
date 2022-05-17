package cat.uvic.teknos.m06.connectorSQL.app.Model;

import java.util.Date;
import java.util.List;

public class PurchaseOrder {
    private int id;
    private Client client;
    private Date data;
    private List<PurchaseLineOrder> lines;

    public PurchaseOrder(int id, Client client, Date data, List<PurchaseLineOrder> lines) {
        this.id = id;
        this.client = client;
        this.data = data;
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
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<PurchaseLineOrder> getLines() {
        return lines;
    }

    public void setLines(List<PurchaseLineOrder> lines) {
        this.lines = lines;
    }
}