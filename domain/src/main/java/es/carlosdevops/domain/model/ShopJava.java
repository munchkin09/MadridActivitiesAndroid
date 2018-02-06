package es.carlosdevops.domain.model;

/**
 * Created by carlosledesma on 23/1/18.
 */

public class ShopJava {
    private int id;
    private String name;

    public ShopJava(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
