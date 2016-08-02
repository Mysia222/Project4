package main.resources;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by potaychuk on 02.08.2016.
 */
public class ViewINPUT {
    public static final String BUNDLE_NAME="labels";
    public static final String WRONG_ENTER = "Wrong enter! Please repeat input login and password.";
    public static ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME , new Locale("uk" ,"UA"));
//    private static ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME , new Locale("en" ,"EN"));

    private static final String PASS_KEY="msg.password";
    public static final String PASS=bundle.getString(PASS_KEY);

    public static final String CREATE_NEW_LOGIN = "Create account";  //index
    public static final String CREATE = "create";                    //CreateUser
    public static final String FIRST_NAME = "First Name";
    public static final String SECOND_NAME = "Create new login";
    public static final String LOGIN = "Create new login";
    public static final String PASSWORD = "Create new login";
}
