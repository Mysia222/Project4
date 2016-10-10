package dao.hibernate;

import dao.DAOException;
import dao.SubsDao;
import ent.SubInfo;
import ent.Subscriber;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Славик on 08.10.2016.
 */
public class SubscriberHibernateDao implements SubsDao {
    private final SessionFactory factory;

    public SubscriberHibernateDao() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public boolean exist(String login, String password) throws DAOException {
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("from SubInfo where login=:login and password=:password");
            query.setParameter("login", login);
            query.setParameter("password", password);
            SubInfo subInfo = (SubInfo) query.uniqueResult();
            return subInfo!=null;
        }
    }

    @Override
    public void create(Subscriber subscriber) throws DAOException {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(subscriber);
        }finally {
            transaction.commit();
            session.close();
        }

    }

    @Override
    public void update(Subscriber subscriber) throws DAOException {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(subscriber);
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
            session.delete(new Subscriber(id,0.0,false,false,null,null));
        }finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public Subscriber find(int id) throws DAOException {
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("from Subscriber where id=:id");
            query.setParameter("id", id);
            return (Subscriber) query.uniqueResult();
        }
    }

    @Override
    public List<Subscriber> findAll() throws DAOException {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            return session.createQuery("from Subscriber").list();
        }finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public Subscriber find(String log) throws DAOException {
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("from SubInfo where login=:log");
            query.setParameter("log", log);
            SubInfo subInfo = (SubInfo) query.uniqueResult();
            return find(subInfo.getId());
        }
    }

    @Override
    public void block(int id) throws DAOException {

    }

    @Override
    public void unlock(int id) throws DAOException {

    }

    @Override
    public void updateBalance(Subscriber subscriber) throws DAOException {

    }

    @Override
    public void updateSubsServices(Subscriber sub) throws DAOException {

    }

    @Override
    public void delete(String login) throws DAOException {

    }
}
