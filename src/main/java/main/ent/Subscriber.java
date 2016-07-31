package main.ent;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Славик on 16.07.2016.
 */
public class Subscriber {


    private double balance;
    private boolean admin;
    private boolean blocked;

    private int contract;
    private SubInfo info;
    private Set<Service> currentService = new TreeSet<Service>();

    public Subscriber() {
    }



    public class SubInfo{

        private String firstName;
        private String secondName;
        private String password;
        private String login;

        public SubInfo() {
        }

        public SubInfo(String firstName, String secondName, String password, String login) {
            this.firstName = firstName;
            this.secondName = secondName;
            this.password = password;
            this.login = login;
        }

        public String getPassword() {
            return password;
        }


        public void setPassword(String password) {
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getSecondName() {
            return secondName;
        }

        public void setSecondName(String secondName) {
            this.secondName = secondName;
        }

        @Override
        public String toString() {
            return "SubInfo{" +
                    "firstName='" + firstName +
                    ", secondName='" + secondName +
                    ", password='" + password +
                    ", login='" + login +
                    '}';
        }
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

