package cat.uvic.teknos.m06.connectorSQL.utilities.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PurchaseLineOrder {
    @Id
    @GeneratedValue
    private int id;
    private int idOrder;
    private Product product;
    private float quantity;
}
