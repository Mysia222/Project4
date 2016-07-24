package main.ent;

/**
 * Created by Славик on 24.07.2016.
 */
public class Service {
    public String name;
    public double price;

    public Service() {
    }

    public Service(String name, double price) {
        this.name = name;
        this.price = price;
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
}
