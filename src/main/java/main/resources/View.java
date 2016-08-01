package main.resources;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Славик on 30.07.2016.
 */
public class View {

    public static final String BUNDLE_NAME="main.resources.labels";
    public static final String WRONG_ENTER = "Wrong enter! Please repeat input login and password.";
    static ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME , new Locale("uk" ,"UA"));

    public static final String PAGE_LOGIN = "login";
    public static final String PAGE_PASSWORD = "password";



    public static final String LOGIN_B = "Login";
    public static final String LOGIN_L = "Login_l";
    public static final String PASSWORD_B = "Password";
    public static final String PASSWORD_L = "Password_l";

    public static final String QUERY_LOGIN = "login";
    public static final String QUERY_SERVICE_NAME = "name";
    public static final String QUERY_SERVICE_PRICE = "price";
    public static final String QUERY_SERVICE_ID = "id";
    public static final String QUERY_PASSWORD = "password";
    public static final String QUERY_BALANCE = "balance";
    public static final String QUERY_CONTRACT = "contract";
    public static final String QUERY_ADMIN = "admin";
    public static final String QUERY_BLOCKED = "blocked";
    public static final String QUERY_DELETED = "deleted";
    public static final String QUERY_SERVICE = "current_service";
    public static final String QUERY_S_NAME = "second_name";
    public static final String QUERY_F_NAME = "first_name";
    public static final String STATUS_BLOCKED = "STATUS: BLOCKED!";

    public static final String CLOSE_EXCEPTION = "Close exception";
    public static final String EXECUTE_EXCEPTION = "Execute exception";

    public static final String DB_URL = "java:comp/env/jdbc/mysql";

    public static ResourceBundle getBundle() {
        return bundle;
    }

    public static void setBundle(ResourceBundle bundle) {
        View.bundle = bundle;
    }


}