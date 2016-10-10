package dao.hibernate;

import dao.DAOException;
import dao.ServicesDao;
import ent.Service;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.xml.registry.infomodel.User;
import java.util.List;

/**
 * Created by Славик on 01.10.2016.
 */
public class ServiceHibernateDao implements ServicesDao{
    private final SessionFactory factory;

    public ServiceHibernateDao() {
        factory = new Configuration().configure().buildSessionFactory();
    }


    @Override
    public void edit(int id,boolean edit) throws DAOException {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query =session.createQuery("update Service set edit =:editValue where id=:id");
        query.setParameter("editValue",edit);
        query.setParameter("id",id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }


    @Override
    public Service find(String name) throws DAOException {
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("from Service where name=:name");
            query.setParameter("name", name);
            return (Service) query.uniqueResult();
        }
//        Session session = factory.openSession();
//        Criteria criteria = session.createCriteria(Service.class);
//        criteria.add(Restrictions.eq("name",name));
//        return (Service) criteria.uniqueResult();
    }

    @Override
    public boolean nameInUse(String name) throws DAOException {
        return find(name) == null;
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
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(new Service(null, 0, id, false));
        }finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public Service find(int id) throws DAOException {
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("from Service where id=:id");
            query.setParameter("id", id);
            return (Service) query.uniqueResult();
        }
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
