package model;

import main.dao.DAOException;
import main.dao.ServicesDao;
import main.dao.SubsDao;
import main.ent.Service;
import main.ent.Subscriber;
import main.dao.DaoFactory;

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
    public List<Subscriber> getSubsList() throws SQLException, NamingException {
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        try {
            return sd.findAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method checks existing of subs in DB by login and password
     * @param login
     * @param password
     * @return
     */
    public boolean existByLogPas(String login, String password){
        DaoFactory df =  DaoFactory.getFactory();
        SubsDao sd =  df.createSubsDao();
        try {
            return sd.findByLogPas(login,password);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This method finds item in DB by ID and creates Subscriber object
     * @param contract is items id
     * @return Subscriber object
     */
    public Subscriber subByContract(int contract){
        DaoFactory df =  DaoFactory.getFactory();
        SubsDao sd =  df.createSubsDao();
        try {
            return sd.find(contract);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method finds item by login and returns Subscriber object
     * @param log is login
     * @return Subscriber object
     * @throws SQLException
     * @throws NamingException
     */
    public Subscriber subByLog(String log) throws SQLException, NamingException {
        DaoFactory df =  DaoFactory.getFactory();
        SubsDao sd =  df.createSubsDao();
        try {
            return sd.getSubByLog(log);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method updates item in DB by  Subscriber object
     * @param subscriber is  Subscriber object
     */
    public void setSub(Subscriber subscriber)  {
        DaoFactory df =  DaoFactory.getFactory();
        SubsDao sd =  df.createSubsDao();
        try {
            sd.update(subscriber);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method blocks item in DB by id
     * @param contract is id
     */
    public void blockId(int contract) {
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        try {
            sd.block(contract);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method unlocks itm in DB by id
     * @param contract
     */
    public void unlockId(int contract) {
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        try {
            sd.unlock(contract);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method creates item in DB by  Subscriber object
     * @param subscriber is Subscriber object
     */
    public void create(Subscriber subscriber) {
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        try {
            sd.create(subscriber);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method update balnce of item in DB by Subscriber object
     * @param sub is Subscriber object
     */
    public void setBalance(Subscriber sub) {
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        try {
            sd.updateBalance(sub);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method sets services to subscriber item in DB by Subscriber object
     * @param sub is Subscriber object
     */
    public void setService(Subscriber sub) {
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        try {
            sd.updateSubsServices(sub);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
