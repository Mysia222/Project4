package views;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Славик on 30.07.2016.
 */
public class View {


    public static final String WRONG_ENTER = "Wrong enter! Please repeat input login and password.";

    //nice
    public static final String BUNDLE_NAME="labels";
    public static final String SUBSCRIBER_SESSION ="subscriber" ;
    public static final String USER_CABINET ="USER_CABINET" ;
    public static final  String ADMIN_CABINET = "SERVICE_ADMIN_CABINET";
    public static final String SERVICE_SUBSCRIBERS = "SERVICE_SUBSCRIBERS";
    public static final String SERVICES_LIST_EDIT = "SERVICE_SERVICES";



    public static ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME , new Locale("uk" ,"UA"));

//    static ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME , new Locale("en" ,"EN"));

    public static final String CONTRACT =  bundle.getString("msg.contract");
    public static final String CONTRACT_PAGE ="contract";
    public static final String CONTRACT_PAGE_H ="contractH";

    public static final String CURRENT_SERVICE =bundle.getString("msg.current_service");
    public static final String CURRENT_SERVICE_PAGE ="current_service";
    public static final String CURRENT_SERVICE_PAGE_H ="current_serviceH";

    public static final String BALANCE =bundle.getString("msg.balance");
    public static final String BALANCE_PAGE ="balance";
    public static final String BALANCE_PAGE_H ="balanceH";

    public static final String BLOCKED_TRUE =bundle.getString("msg.blocked_true");
    public static final String BLOCKED_FALSE =bundle.getString("msg.blocked_false");
    public static final String BLOCKED_PAGE ="blocked";

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

    public static final String DIRECT_SERVICE=bundle.getString("msg.direct_service_button");
    public static final String DIRECT_SERVICE_BUTTON="direct_service_button";
    public static final String DIRECT_SERVICE_PAGE="direct_service";


    public static final String WRONG_LOGIN=bundle.getString("msg.wrongLogin");
    public static final String WRONG_PASSWORD=bundle.getString("msg.wrongPassword");
    public static final String WRONG_LOGIN_PAGE="wrongLogin";
    public static final String PREPARED_LOGIN="preparedLogin";

    public static final String SET_USER_SERVICE=bundle.getString("msg.set_service");
    public static final String SET_USER_SERVICE_PAGE="set_service";

    public static final String REMOVE_USER_SERVICE=bundle.getString("msg.remove_service");
    public static final String REMOVE_USER_SERVICE_PAGE="remove_service";

    public static final String PRICE = bundle.getString("msg.price");
    public static final String PRICE_PAGE = "price";

    public static final String NAME = bundle.getString("msg.name");
    public static final String NAME_PAGE = "name";

    public static final String ENABLE_ACTIONS = bundle.getString("msg.enableAction");
    public static final String ENABLE_ACTIONS_PAGE = "enableAction";

    public static final String ID_PAGE = "id";

    public static final String FOOT_THE_BILL_PAGE="foot_the_bill_button";
    public static final String FOOT_THE_BILL_BUTTON="foot_the_bill";
    public static final String FOOT_THE_BILL=bundle.getString("msg.foot_the_bill");

    public static final String MONEY_VALUE_L = bundle.getString("msg.moneyValue");
    public static final String MONEY_VALUE_PAGE_L = "moneyValue";

    public static final String MONEY_VALUE_PAGE = "addToBalance";

    public static final String MONEY_PAY = bundle.getString("msg.payMoney");
    public static final String MONEY_PAY_PAGE = "payMoney";

    public static final String LOGOUT = bundle.getString("msg.logout");
    public static final String LOGOUT_PAGE = "logout";
    public static final String LOGOUT_BUTTON = "logout";

    public static final String RETURN_CABINET = bundle.getString("msg.toTheCabinet");
    public static final String RETURN_CABINET_PAGE = "toTheCabinet";

    public static final String DIRECT_INFO = bundle.getString("msg.direct_info");
    public static final String DIRECT_INFO_BUTTON = "direct_info";


    public static final String SAVE = bundle.getString("msg.save");
    public static final String SAVE_PAGE = "save";

    public static final String SAVED_FIRST_NAME = "savedFName";
    public static final String SAVED_SECOND_NAME = "savedSName";
    public static final String SAVED_PASSWORD = "savedPassword";
    public static final String SAVED_LOGIN = "savedLogin";


    public static final String ADMIN_VIEW_SUBS_BUTTON = "showSubs";
    public static final String ADMIN_VIEW_SUBS = bundle.getString("msg.showSubs");

    public static final String ADMIN_VIEW_SERVICES_BUTTON = "showServices";
    public static final String ADMIN_VIEW_SERVICES = bundle.getString("msg.showServices");

    public static final String ADMIN_CREATE_SERVICE_BUTTON = "createService";
    public static final String ADMIN_CREATE_SERVICE = bundle.getString("msg.createService");

    public static final String USER_LIST_PAGE = "users";
    public static final String SERVICES_LIST_PAGE = "services";


    public static final String BLOCK_SUBSCRIBER_BUTTON = "blockSubscriber";
    public static final String BLOCK_SUBSCRIBER = bundle.getString("msg.blockSubscriber");

    public static final String UNLOCK_SUBSCRIBER_BUTTON = "unlockSubscriber";
    public static final String UNLOCK_SUBSCRIBER = bundle.getString("msg.unlockSubscriber");

    public static final String REFRESH_BUTTON ="refresh";
    public static final String REFRESH = bundle.getString("msg.refresh");

    public static final String CREATE_SERVICE_BUTTON ="createService";
    public static final String CREATE_SERVICE = bundle.getString("msg.createService");

    public static final String DELETE_PAGE ="delete";
    public static final String DELETE = bundle.getString("msg.delete");

    public static final String CHANGE_PAGE ="change";
    public static final String CHANGE = bundle.getString("msg.change");

    public static final String NAME_IN_USE = bundle.getString("msg.nameInUse");;
    public static final String NAME_IN_USE_PAGE = "nameInUse";




//*****************************************
//    public static final String PAGE_LOGIN = "login";
//    public static final String PAGE_PASSWORD = "password";



//    public static final String LOGIN_B = "Login";
//    public static final String LOGIN_L = "Login_l";
//    public static final String PASSWORD_B = "Password";
//    public static final String PASSWORD_L = "Password_l";

    public static final String QUERY_LOGIN = "login";
    public static final String QUERY_SERVICE_NAME = "name";
    public static final String QUERY_SERVICE_PRICE = "price";
    public static final String QUERY_SERVICE_ID = "id";
    public static final String QUERY_SERVICE_EDIT = "edit" ;
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

//    public static ResourceBundle getBundle() {
//        return bundle;
//    }

    public static void setBundle(ResourceBundle bundle) {
        View.bundle = bundle;
    }

//    public static void setLocale(Locale locale){
//        View.setBundle(ResourceBundle.getBundle(BUNDLE_NAME,locale));
//    }
}