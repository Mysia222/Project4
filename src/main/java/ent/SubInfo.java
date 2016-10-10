package ent;

/**
* Created by Славик on 08.10.2016.
*/
public class SubInfo{

    /**
     * Sub's id
     */
    private int id;

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


   private Subscriber subscriber;



   /**
    * Default constructor
    */
   public SubInfo() {
   }

   /**
    * Constructor
    * @param id is id
    * @param firstName is first name
    * @param secondName is second name
    * @param password is password
    * @param login is login
    */
   public SubInfo(int id, String firstName, String secondName, String password, String login) {
       this.id = id;
       this.firstName = firstName;
       this.secondName = secondName;
       this.password = password;
       this.login = login;
   }

   //getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public String toString() {
        return "SubInfo{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubInfo subInfo = (SubInfo) o;
        return id == subInfo.id && (firstName != null ? firstName.equals(subInfo.firstName) : subInfo.firstName == null
                && (secondName != null ? secondName.equals(subInfo.secondName) : subInfo.secondName == null
                && (password != null ? password.equals(subInfo.password) : subInfo.password == null
                && (login != null ? login.equals(subInfo.login) : subInfo.login == null))));
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }
}
