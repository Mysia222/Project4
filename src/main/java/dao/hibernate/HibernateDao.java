package dao.hibernate;

import dao.DAOException;
import dao.ServicesDao;
import ent.Service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.xml.registry.infomodel.User;
import java.util.List;

/**
 * Created by Славик on 01.10.2016.
 */
public class HibernateDao implements ServicesDao{
    private final SessionFactory factory;

    public HibernateDao() {
        factory = new Configuration().configure().buildSessionFactory();
    }


    @Override
    public void edit(int id) throws DAOException {

    }

    @Override
    public void unEdit(int id) throws DAOException {

    }

    @Override
    public Service find(String name) throws DAOException {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            final Query query = session.createQuery("from Service as service where service.name=:name");
            query.setString("name", name);
            return (Service) query.iterate().next();
        }finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public boolean nameInUse(String name) throws DAOException {
        return false;
    }

    @Override
    public void create(Service service) throws DAOException {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(service);
        }finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void update(Service service) throws DAOException {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(service);
        }finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void delete(int id) throws DAOException {

    }

    @Override
    public Service find(int id) throws DAOException {
        return null;
    }

    @Override
    public List<Service> findAll() throws DAOException {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
           return session.createQuery("from Service").list();
        }finally {
            transaction.commit();
            session.close();
        }

    }
}
