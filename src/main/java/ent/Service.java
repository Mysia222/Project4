package ent;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Славик on 24.07.2016.
 */
public class Service {

    /**
     * It's name of service
     */
    private String name;

    /**
     * It's price of service
     */
    private double price;

    /**
     * It's id of service
     */
    private int id;

    /**
     * It's editing status
     */
    private boolean edit;

    private Set<Subscriber> subs = new HashSet<>();

    /**
     * Default constructor
     */
    public Service() {
    }

    /**
     * Constructor
     * @param name is name
     * @param price is price
     * @param id is id
     */
    public Service(String name, double price, int id, boolean edit) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.edit = edit;
    }

    //setters & getters


    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Subscriber> getSubs() {
        return subs;
    }

    public void setSubs(Set<Subscriber> subs) {
        this.subs = subs;
    }

    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Service temp = (Service)obj;
        return temp.getId()==this.getId();
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}
