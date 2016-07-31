package model;

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

    private static SubService instance = new SubService();
    public static SubService getInstance(){return instance;}
    private SubService(){}

    public List<Subscriber> getSubsList() throws SQLException, NamingException {
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        return sd.findAll();
    }

    public boolean existByLogPas(String login, String password){
        DaoFactory df =  DaoFactory.getFactory();
        SubsDao sd =  df.createSubsDao();
        return sd.findByLogPas(login,password);
    }

    public Subscriber subByContract(int contract){
        DaoFactory df =  DaoFactory.getFactory();
        SubsDao sd =  df.createSubsDao();
        return sd.find(contract);
    }


    public Subscriber subByLog(String log) throws SQLException, NamingException {
        DaoFactory df =  DaoFactory.getFactory();
        SubsDao sd =  df.createSubsDao();
        Subscriber sub =  sd.getSubByLog(log);
        return sub;
    }


    public void setSub(Subscriber subscriber)  {
        DaoFactory df =  DaoFactory.getFactory();
        SubsDao sd =  df.createSubsDao();
        sd.update(subscriber);
    }

    public List<Subscriber> getDebtorsList() {
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        return sd.getDebtors();

    }

    public void blockId(int contract) {
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        sd.block(contract);
    }
    public void unlockId(int contract) {
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        sd.unlock(contract);
    }

    public void create(Subscriber subscriber) {
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        sd.create(subscriber);
    }

    public void setBalance(Subscriber sub) {
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        sd.updateBalance(sub);
    }

    public void setService(Subscriber sub) {
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        sd.updateSubsServices(sub);
    }
}
