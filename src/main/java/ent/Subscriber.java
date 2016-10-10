package ent;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Славик on 16.07.2016.
 */
public class Subscriber {

    /**
     * It's balance of subscriber
     */
    private double balance;

    /**
     * Admin status
     */
    private boolean admin;

    /**
     * Block status
     */
    private boolean blocked;

    /**
     * It's id of subscriber
     */
    private int contract;

    /**
     * It's information of subscriber which subscriber can edit
     */
    private SubInfo info;

    /**
     * Set of subscriber's services
     */
    private Set<Service> currentService = new HashSet<>();


    /**
     * Default constructor
     */
    public Subscriber() {
    }

    public Subscriber( int contract,double balance, boolean admin, boolean blocked, SubInfo info, Set<Service> currentService) {
        this.balance = balance;
        this.admin = admin;
        this.blocked = blocked;
        this.contract = contract;
        this.info = info;
        this.currentService = currentService;
    }

    public Set<Service> getCurrentService() {
        return currentService;
    }

    public void setCurrentService(Set<Service> currentService) {
        this.currentService = currentService;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public SubInfo getInfo() {
        return info;
    }

    public void setInfo(SubInfo info) {
        this.info = info;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getContract() {
        return contract;
    }

    public void setContract(int contract) {
        this.contract = contract;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "balance=" + balance +
                ", admin=" + admin +
                ", blocked=" + blocked +
                ", contract=" + contract +
                ", info=" + info +
                ", currentService=" + currentService +
                '}';
    }
}

