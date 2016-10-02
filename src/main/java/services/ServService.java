package services;

import dao.DAOException;
import dao.DaoFactory;
import dao.ServicesDao;
import dao.hibernate.HibernateDao;
import dao.jdbc.JdbcServiceDao;
import ent.Service;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Славик on 31.07.2016.
 */
public class ServService {

    /**
     * Instance
     */
    private static ServService instance = new ServService();

    /**
     * This method returns Instance
     * @return
     */
    public static ServService getInstance(){return instance;}

    /**
     * Private default constructor
     */
    private ServService(){}

    /**
     * This method returns list of all services
     * @return
     * @throws DAOException
     */
    public List<Service> getServiceList() throws DAOException {
        DaoFactory df = DaoFactory.getFactory();
        ServicesDao sd = df.createServicesDao();

        return sd.findAll();
    }

    /**
     * This method finds by id and returns Service
     * @param id is Service's id
     * @return
     */
    public Service getService(int id)  throws DAOException {
        DaoFactory df =  DaoFactory.getFactory();
        ServicesDao sd =  df.createServicesDao();
        return sd.find(id);
    }

    /**
     * This method create an item in DB by Service object
     * @param service is Service object
     */
    public void create(Service service)  throws DAOException {
        DaoFactory df = DaoFactory.getFactory();
        ServicesDao sd = df.createServicesDao();
//        HibernateDao sd =  new HibernateDao();
        sd.create(service);
    }

    /**
     * This method deletes service item from DB by id
     * @param id is id
     */
    public void delete(int id) throws DAOException {
        DaoFactory df = DaoFactory.getFactory();
        ServicesDao sd = df.createServicesDao();
        sd.delete(id);
    }

    /**
     * This method updates service item in DB by service object
     * @param service is service object
     */
    public void update(Service service) throws DAOException {
        DaoFactory df = DaoFactory.getFactory();
        ServicesDao sd = df.createServicesDao();
        sd.update(service);
    }

    public void editService(int id) throws DAOException {
        DaoFactory df = DaoFactory.getFactory();
        ServicesDao sd = df.createServicesDao();
        sd.edit(id);
    }

    public void unEditService(int id) throws DAOException {
        DaoFactory df = DaoFactory.getFactory();
        ServicesDao sd = df.createServicesDao();
        sd.unEdit(id);
    }

    public boolean nameInUse(String name) throws DAOException {
        DaoFactory df = DaoFactory.getFactory();
        ServicesDao sd = df.createServicesDao();
        return sd.nameInUse(name);
    }
}
