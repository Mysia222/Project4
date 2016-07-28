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
public class TelService {

//    Logger log = LogManager.getLogger(TelService.class);


    private static TelService instance = new TelService();
    public static TelService getInstance(){return instance;}
    private TelService(){}

     public List<Service> getServiceList() throws SQLException, NamingException {
         DaoFactory df = DaoFactory.getFactory();
         ServicesDao sd = df.createServicesDao();
         return sd.findAll();
    }

    public List<Subscriber> getSubsList() throws SQLException, NamingException {
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        return sd.findAll();
    }

    public boolean existByLogPas(String login, String password){
        DaoFactory df =  DaoFactory.getFactory();
        SubsDao sd =  df.createSubsDao();
        return sd.existByLogPas(login,password);
    }

    public Subscriber subByContract(int contract){
        DaoFactory df =  DaoFactory.getFactory();
        SubsDao sd =  df.createSubsDao();
        return (Subscriber)sd.find(contract);
    }


    public Subscriber subByLog(String log) throws SQLException, NamingException {
        DaoFactory df =  DaoFactory.getFactory();
        SubsDao sd =  df.createSubsDao();
        Subscriber sub =  sd.getSubByLog(log);
        return sub;
    }

    public Service getService(int id){
        DaoFactory df =  DaoFactory.getFactory();
        ServicesDao sd =  df.createServicesDao();
        Service service = (Service) sd.find(id);
        return service;
    }

    public void setSub(Subscriber subscriber)  {
        DaoFactory df =  DaoFactory.getFactory();
        SubsDao sd =  df.createSubsDao();
        sd.update(subscriber);
//        Statement statement = new JDBCRunner().getConnection().createStatement();
//        String s = "UPDATE daotalk.abonents SET login='"+subscriber.getInfo().getLogin()+
//                "' ,password='"+subscriber.getInfo().getPassword()+"' ,first_name='"+subscriber.getInfo().getFirstName()+
//                "', second_name='"+subscriber.getInfo().getSecondName()+"' WHERE contract='"+subscriber.getContract()+"';";
//        statement.execute(s);
    }

    public List<Subscriber> getDebtorsList() {
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        return sd.getDebtors();

    }

    public void blockId(int contract) {
        DaoFactory df = DaoFactory.getFactory();
        SubsDao sd = df.createSubsDao();
        sd.blockId(contract);
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
}
