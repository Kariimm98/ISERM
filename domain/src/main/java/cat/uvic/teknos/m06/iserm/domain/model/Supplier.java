package cat.uvic.teknos.m06.iserm.domain.model;


import javax.persistence.*;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToOne(mappedBy ="Address", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private String address;

    public Supplier(){

    }

    public Supplier(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}