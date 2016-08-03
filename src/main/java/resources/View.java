package resources;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Славик on 30.07.2016.
 */
public class View {

    public static final String BUNDLE_NAME="labels";
    public static final String WRONG_ENTER = "Wrong enter! Please repeat input login and password.";

    static ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME , new Locale("uk" ,"UA"));

//    static ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME , new Locale("en" ,"EN"));

    public static final String CONTRACT =bundle.getString("msg.contract");
    public static final String CONTRACT_PAGE ="contract";
    public static final String CONTRACT_PAGE_H ="contractH";

    public static final String CURRENT_SERVICE =bundle.getString("msg.current_service");
    public static final String CURRENT_SERVICE_PAGE ="current_service";
    public static final String CURRENT_SERVICE_PAGE_H ="current_serviceH";

    public static final String BALANCE =bundle.getString("msg.balance");
    public static final String BALANCE_PAGE ="balance";
    public static final String BALANCE_PAGE_H ="balanceH";

    public static final String PASSWORD =bundle.getString("msg.password");
    public static final String PASSWORD_PAGE ="password";
    public static final String PASSWORD_PAGE_H ="passwordH";

    public static final String LOGIN=bundle.getString("msg.login");
    public static final String LOGIN_PAGE="login";
    public static final String LOGIN_PAGE_H="loginH";

    public static final String FIRST_NAME=bundle.getString("msg.fName");
    public static final String FIRST_NAME_PAGE="fName";
    public static final String FIRST_NAME_PAGE_H="fNameH";

    public static final String SECOND_NAME=bundle.getString("msg.sName");
    public static final String SECOND_NAME_PAGE="sName";
    public static final String SECOND_NAME_PAGE_H="sNameH";

    public static final String LOGIN_IN_USE=bundle.getString("msg.loginInUse");
    public static final String LOGIN_IN_USE_PAGE="loginInUse";

    public static final String USER_INFO=bundle.getString("msg.userInfo");
    public static final String USER_INFO_PAGE="userInfo";

    public static final String CREATE_ACCOUNT=bundle.getString("msg.create_account");   //index
    public static final String CREATE_ACCOUNT_PAGE="create_account";
    //index
    public static final String ENTER=bundle.getString("msg.enter");
    public static final String ENTER_PAGE="submit_enter";

    public static final String CREATE=bundle.getString("msg.create");
    public static final String CREATE_PAGE="create";


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

    public static void setLocale(Locale locale){
        View.setBundle(ResourceBundle.getBundle(BUNDLE_NAME,locale));
    }
}