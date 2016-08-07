package services;

import dao.DAOException;
import dao.SubsDao;
import ent.Subscriber;
import dao.DaoFactory;

import javax.naming.NamingException;
import java.sql.*;
import java.util.List;


/**
 * Created by Славик on 17.07.2016.
 */
public class SubService {

    /**
     * Instance
     */
    private static SubService instance = new SubService();

    /**
     * This method returns Instance
     * @return
     */
    public static SubService getInstance(){return instance;}

    /**
     * Private default constructor
     */
    private SubService(){}

    /**
     * This method returns list of all subscribers
     * @return
     * @throws SQLException
     * @throws NamingException
     */
    public List<Subscriber> getSubsList() throws DAOException{
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        return sd.findAll();

    }

    /**
     * This method checks existing of subs in DB by login and password
     * @param login
     * @param password
     * @return
     */
    public boolean exist(String login, String password) throws DAOException{
        DaoFactory df =  DaoFactory.getFactory();
        SubsDao sd =  df.createSubsDao();
        return sd.exist(login,password);
    }

    /**
     * This method finds item in DB by ID and creates Subscriber object
     * @param contract is items id
     * @return Subscriber object
     */
    public Subscriber find(int contract) throws DAOException{
        DaoFactory df =  DaoFactory.getFactory();
        SubsDao sd =  df.createSubsDao();
        return sd.find(contract);
    }

    /**
     * This method finds item by login and returns Subscriber object
     * @param log is login
     * @return Subscriber object
     * @throws SQLException
     * @throws NamingException
     */
    public Subscriber find(String log) throws DAOException{
        DaoFactory df =  DaoFactory.getFactory();
        SubsDao sd =  df.createSubsDao();
        return sd.find(log);
    }

    /**
     * This method updates item in DB by  Subscriber object
     * @param subscriber is  Subscriber object
     */
    public void setSub(Subscriber subscriber) throws DAOException{
        DaoFactory df =  DaoFactory.getFactory();
        SubsDao sd =  df.createSubsDao();
        sd.update(subscriber);
    }

    /**
     * This method blocks item in DB by id
     * @param contract is id
     */
    public void blockId(int contract) throws DAOException{
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        sd.block(contract);
    }

    /**
     * This method unlocks itm in DB by id
     * @param contract
     */
    public void unlockId(int contract) throws DAOException{
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        sd.unlock(contract);
    }

    /**
     * This method creates item in DB by  Subscriber object
     * @param subscriber is Subscriber object
     */
    public void create(Subscriber subscriber) throws DAOException{
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        sd.create(subscriber);
    }

    /**
     * This method update balnce of item in DB by Subscriber object
     * @param sub is Subscriber object
     */
    public void setBalance(Subscriber sub) throws DAOException{
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        sd.updateBalance(sub);
    }

    /**
     * This method sets services to subscriber item in DB by Subscriber object
     * @param sub is Subscriber object
     */
    public void setService(Subscriber sub) throws DAOException{
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        sd.updateSubsServices(sub);
    }
}
