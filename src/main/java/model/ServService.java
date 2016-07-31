package model;

import main.dao.DaoFactory;
import main.dao.ServicesDao;
import main.dao.SubsDao;
import main.ent.Service;
import main.ent.Subscriber;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Славик on 31.07.2016.
 */
public class ServService {
    private static ServService instance = new ServService();
    public static ServService getInstance(){return instance;}
    private ServService(){}

    public List<Service> getServiceList() throws SQLException, NamingException {
        DaoFactory df = DaoFactory.getFactory();
        ServicesDao sd = df.createServicesDao();
        return sd.findAll();
    }


    public Service getService(int id){
        DaoFactory df =  DaoFactory.getFactory();
        ServicesDao sd =  df.createServicesDao();
        Service service = sd.find(id);
        return service;
    }

    public void create(Service service) {
        DaoFactory df = DaoFactory.getFactory();
        ServicesDao sd = df.createServicesDao();
        sd.create(service);
    }

    public void delete(int id){
        DaoFactory df = DaoFactory.getFactory();
        ServicesDao sd = df.createServicesDao();
        sd.delete(id);
    }

    public void update(Service service) {
        DaoFactory df = DaoFactory.getFactory();
        ServicesDao sd = df.createServicesDao();
        sd.update(service);
    }
}
