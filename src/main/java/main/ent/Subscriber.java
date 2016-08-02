package main.ent;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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

    /**
     * This class describe subscriber
     */
    public class SubInfo{

        /**
         * Sub's firs name
         */
        private String firstName;

        /**
         * Sub's second name
         */
        private String secondName;

        /**
         * Sub's password
         */
        private String password;

        /**
         * Sub's login
         */
        private String login;

        /**
         * Default constructor
         */
        public SubInfo() {
        }

        /**
         * Constructor
         * @param firstName is first name
         * @param secondName is second name
         * @param password is password
         * @param login is login
         */
        public SubInfo(String firstName, String secondName, String password, String login) {
            this.firstName = firstName;
            this.secondName = secondName;
            this.password = password;
            this.login = login;
        }

        //getters & setters
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SubInfo subInfo = (SubInfo) o;

            return firstName.equals(subInfo.firstName) && secondName.equals(subInfo.secondName) &&
                    password.equals(subInfo.password) && login.equals(subInfo.login);

        }

        @Override
        public int hashCode() {
            int result = firstName.hashCode();
            result = 31 * result + secondName.hashCode();
            result = 31 * result + password.hashCode();
            result = 31 * result + login.hashCode();
            return result;
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

