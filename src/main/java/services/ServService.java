package services;

import dao.DAOException;
import dao.DaoFactory;
import dao.ServicesDao;
import ent.Service;

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
//        ServiceHibernateDao sd =  new ServiceHibernateDao();
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

    public void editService(int id, boolean edit) throws DAOException {
        DaoFactory df = DaoFactory.getFactory();
        ServicesDao sd = df.createServicesDao();
        sd.edit(id, edit);
    }


    public boolean nameInUse(String name) throws DAOException {
        DaoFactory df = DaoFactory.getFactory();
        ServicesDao sd = df.createServicesDao();
        return sd.nameInUse(name);
    }
}
