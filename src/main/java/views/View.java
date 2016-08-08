package views;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Славик on 30.07.2016.
 */
public class View {

    //nice
    public static final String BUNDLE_NAME="labels";
    public static final String BUNDLE = "bundle";
    public static final String COUNTRY_PAGE = "country";
    public static final String UA = "UA";
    public static final String DRAW_INDEX = "DRAW_INDEX";
    public static final String LOG_DATA_SOURCE_EXCEPTION = "Can't return Data source connection!";

    public static Locale localeUA = new Locale("uk","UA");
    public static Locale localeEN = new Locale("en","EN");

    //    public static ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME , new Locale("uk" ,"UA"));
    public static final String WRONG_ENTER = "Wrong enter! Please repeat input login and password.";
    public static final String SUBSCRIBER_SESSION ="subscriber" ;
    public static final String USER_CABINET ="USER_CABINET" ;
    public static final String ADMIN_CABINET = "SERVICE_ADMIN_CABINET";
    public static final String SERVICE_SUBSCRIBERS = "SERVICE_SUBSCRIBERS";
    public static final String SERVICES_LIST_EDIT = "SERVICE_SERVICES";
    public static final String USER_DIRECT_SERVICE = "USER_DIRECT_SERVICE";
    public static final String CREATE_NEW_FLAG = "createFlag";
    public static final String CREATE_NEW_FLAG_BUTTON = "newFlag";
    public static final String CREATE_NEW_FLAG_TRUE = "true";
    public static final String CREATE_NEW_FLAG_FALSE = "false";
    public static final String JUST_CREATED_SERVICE_NAME = "New Service";
    public static final String LOG_CREATE_SUBSCRIBER = "Creating new subscriber.";
    public static final String LOG_CONNECTED = "Successfully connected to Data Source.";
    public static final String LOG_INIT_SERVICE = "Initializing subscriber's service(Default), sub: ";
    public static final String GET_SUB_BY_LOGIN = "Getting subscriber by login: ";
    public static final String LOG_GET_SUBS_SERVICES = "Getting subscriber's services, id: ";
    public static final String LOG_FINISHED = " Finished without Exceptions!";
    public static final String  LOG_FIND_ALL_SUBSCRIBERS = "Searching for all subscribers.";
    public static final String LOG_FIND_SUBSCRIBER = "Searching for subscriber, id: ";
    public static final String JDBS_DAO_FACTORY = "dao.jdbc.JdbcDaoFactory";
    public static final String LOG_GET_FACTORY = "Getting DAOFactory.";
    public static final String LOG_DAO_FACTORY_EXCEPTION = "Can't get DAOFactory!";
    public static final String ERROR_CAUSE = "cause";
    public static final String CANT_DO_REQUEST = "msg.errorCause";




    public static final String CONTRACT =  "msg.contract";
    public static final String CONTRACT_PAGE ="contract";
    public static final String CONTRACT_PAGE_H ="contractH";

    public static final String CURRENT_SERVICE ="msg.current_service";
    public static final String CURRENT_SERVICE_PAGE ="current_service";
    public static final String CURRENT_SERVICE_PAGE_H ="current_serviceH";

    public static final String BALANCE ="msg.balance";
    public static final String BALANCE_PAGE ="balance";
    public static final String BALANCE_PAGE_H ="balanceH";


    public static  String BLOCKED_TRUE ="msg.blocked_true";
    public static  String BLOCKED_FALSE ="msg.blocked_false";
    public static final String BLOCKED_PAGE ="blocked";

    public static final String PASSWORD ="msg.password";
    public static final String PASSWORD_PAGE ="password";
    public static final String PASSWORD_PAGE_H ="passwordH";

    public static final String LOGIN="msg.login";
    public static final String LOGIN_PAGE="login";
    public static final String LOGIN_PAGE_H="loginH";

    public static final String SUBS_COUNT ="msg.subs_count" ;

    public static  String FIRST_NAME="msg.fName";
    public static final String FIRST_NAME_PAGE="fName";
    public static final String FIRST_NAME_PAGE_H="fNameH";

    public static final String SECOND_NAME="msg.sName";
    public static final String SECOND_NAME_PAGE="sName";
    public static final String SECOND_NAME_PAGE_H="sNameH";

    public static final String LOGIN_IN_USE="msg.loginInUse";
    public static final String LOGIN_IN_USE_PAGE="loginInUse";

    public static final String USER_INFO="msg.userInfo";
    public static final String USER_INFO_PAGE="userInfo";

    public static final String CREATE_ACCOUNT="msg.create_account";   //index
    public static final String CREATE_ACCOUNT_PAGE="create_account";
    public static final String CREATE_ACCOUNT_PAGE_DEFAULT="Create account";
    //index
    public static final String ENTER="msg.enter";
    public static final String ENTER_PAGE="submit_enter";

    public static final String CREATE="msg.create";
    public static final String CREATE_PAGE="create";

    public static final String DIRECT_SERVICE="msg.direct_service_button";
    public static final String DIRECT_SERVICE_BUTTON="direct_service_button";
    public static final String DIRECT_SERVICE_PAGE="direct_service";


    public static final String WRONG_LOGIN="msg.wrongLogin";
    public static final String WRONG_PASSWORD="msg.wrongPassword";
    public static final String WRONG_LOGIN_PAGE="wrongLogin";
    public static final String PREPARED_LOGIN="preparedLogin";

    public static final String SET_USER_SERVICE="msg.set_service";
    public static final String SET_USER_SERVICE_PAGE="set_service";

    public static final String REMOVE_USER_SERVICE="msg.remove_service";
    public static final String REMOVE_USER_SERVICE_PAGE="remove_service";

    public static final String PRICE = "msg.price";
    public static final String PRICE_PAGE = "price";

    public static final String NAME = "msg.name";
    public static final String NAME_PAGE = "name";

    public static final String ENABLE_ACTIONS = "msg.enableAction";
    public static final String ENABLE_ACTIONS_PAGE = "enableAction";

    public static final String ID_PAGE = "id";

    public static final String FOOT_THE_BILL_PAGE="foot_the_bill_button";
    public static final String FOOT_THE_BILL_BUTTON="foot_the_bill";
    public static final String FOOT_THE_BILL="msg.foot_the_bill";

    public static final String MONEY_VALUE_L = "msg.moneyValue";
    public static final String MONEY_VALUE_PAGE_L = "moneyValue";

    public static final String MONEY_VALUE_PAGE = "addToBalance";

    public static final String MONEY_PAY = "msg.payMoney";
    public static final String MONEY_PAY_PAGE = "payMoney";

    public static final String LOGOUT = "msg.logout";
    public static final String LOGOUT_PAGE = "logout";
    public static final String LOGOUT_BUTTON = "logout";

    public static final String RETURN_CABINET = "msg.toTheCabinet";
    public static final String RETURN_CABINET_PAGE = "toTheCabinet";

    public static final String DIRECT_INFO = "msg.direct_info";
    public static final String DIRECT_INFO_BUTTON = "direct_info";


    public static final String SAVE = "msg.save";
    public static final String SAVE_PAGE = "save";

    public static final String SAVED_FIRST_NAME = "savedFName";
    public static final String SAVED_SECOND_NAME = "savedSName";
    public static final String SAVED_PASSWORD = "savedPassword";
    public static final String SAVED_LOGIN = "savedLogin";


    public static final String ADMIN_VIEW_SUBS_BUTTON = "showSubs";
    public static final String ADMIN_VIEW_SUBS = "msg.showSubs";

    public static final String ADMIN_VIEW_SERVICES_BUTTON = "showServices";
    public static final String ADMIN_VIEW_SERVICES = "msg.showServices";

    public static final String ADMIN_CREATE_SERVICE_BUTTON = "createService";
    public static final String ADMIN_CREATE_SERVICE = "msg.createService";

    public static final String USER_LIST_PAGE = "users";
    public static final String SERVICES_LIST_PAGE = "services";


    public static final String BLOCK_SUBSCRIBER_BUTTON = "blockSubscriber";
    public static final String BLOCK_SUBSCRIBER = "msg.blockSubscriber";

    public static final String UNLOCK_SUBSCRIBER_BUTTON = "unlockSubscriber";
    public static final String UNLOCK_SUBSCRIBER = "msg.unlockSubscriber";

    public static final String REFRESH_BUTTON ="refresh";
    public static final String REFRESH = "msg.refresh";

    public static final String CREATE_SERVICE_BUTTON ="createService";
    public static final String CREATE_SERVICE = "msg.createService";

    public static final String DELETE_PAGE ="delete";
    public static final String DELETE = "msg.delete";

    public static final String USER_SERVICE_IN_USE_PAGE = "serviceInUse";
    public static final String USER_SERVICE_IN_USE = "msg.serviceInUse";

    public static final String CHANGE_PAGE ="change";
    public static final String CHANGE = "msg.change";

    public static final String NAME_IN_USE = "msg.nameInUse";
    public static final String NAME_IN_USE_PAGE = "nameInUse";

    public static final String EDIT_SERVICE_NAME_FIRST = "msg.editServiceNameFirst";




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
    public static final String QUERY_S_NAME = "second_name";
    public static final String QUERY_F_NAME = "first_name";
    public static final String QUERY_SUB_SERVICE_ID = "service_id";

    public static final String CLOSE_EXCEPTION = "Close exception";
    public static final String LOG_EXECUTE_EXCEPTION = "Execute exception";

    public static final String DB_URL = "java:comp/env/jdbc/mysql";


    public static String LOG_FIND_BY_LOG_PAS = "Checking for login and password in DB: ";
    public static final String LOG_FIND_BY_LOG_PAS_EXCEPTION = "Exception while searching subscriber by login and password!";
    public static String LOG_DELETE_SUBSCRIBER = "Deleting subscriber, id: ";
    public static String LOG_RESULT = "Result: ";
    public static String LOG_LOCK_SUBSCRIBER = "Locking subscriber, id: ";
    public static String LOG_UNLOCK_SUBSCRIBER= "Unlocking subscriber, id: ";
    public static String LOG_UPDATE_SUBSCRIBERS_BALANCE="Updating subscriber's balance: ";
    public static String LOG_UPDATE_SUBSCRIBER="Updating subscriber: ";
    public static String LOG_DELETE_SUBSCRIBERS_SERVICES = "Deleting subscriber's service, id: ";
    public static String LOG_UPDATE_SUBSCRIBERS_SERVICES = "Updating subscriber's service, sub: ";
    public static String LOG_NAME_IN_USE="Checking for using name: ";
    public static String LOG_SET_UNEDIT_TO_SERVICE = "Setting edit FALSE to service, id: ";
    public static String LOG_SET_EDIT_TO_SERVICE = "Setting edit TRUE to service, id: ";
    public static String LOG_DELETE_SERVICE = "Deleting service, id: ";
    public static String LOG_UPDATE_SERVICE  = "Updating service: ";
    public static String LOG_CREATE_SERVICE = "Creating service: ";
    public static String LOG_FIND_ALL_SERVICE = "Searching for all services: ";
    public static String LOG_FIND_SERVICE= "Searching for service, param: ";

}
