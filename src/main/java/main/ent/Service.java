package main.ent;

/**
 * Created by Славик on 24.07.2016.
 */
public class Service {

    /**
     * It's name of service
     */
    public String name;

    /**
     * It's price of service
     */
    public double price;

    /**
     * It's id of service
     */
    public int id;

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
    public Service(String name, double price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    //setters & getters
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
