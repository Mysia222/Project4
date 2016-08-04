package dao;

/**
 * Created by Славик on 01.08.2016.
 */
public class DAOException extends Exception {


    /**
     * Constructor
     * @param s is string message
     * @param e is exception
     */
    public DAOException(String s, Exception e) {
        super(s,e);
    }
}
