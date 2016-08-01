package main.dao.jdbc;

import main.dao.DAOException;
import main.dao.SubsDao;
import main.ent.Service;
import main.ent.Subscriber;
import main.resources.View;
import model.ServService;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Славик on 26.07.2016.
 */
public class JdbcSubsDao implements SubsDao {

    /**
     * Logger
     */
    private static Logger log =  Logger.getLogger(JdbcSubsDao.class);//LogManager.getLogger(JdbcSubsDao.class.getName());

    /**
     * This method creates new item in DB
     * @param sub is Subscriber of item
     * @throws DAOException
     */
    public void create(Subscriber sub) throws DAOException {
        Connection connection = null;
        PreparedStatement query = null;
        String s = "INSERT INTO daotalk.abonents  (first_name, second_name, password, login) VALUES (?,?,?,?)";
        try {
            connection = JdbcDaoFactory.getConnection();
            query = connection.prepareStatement(s);
            query.setString(1, sub.getInfo().getFirstName());
            query.setString(2, sub.getInfo().getSecondName());
            query.setString(3, sub.getInfo().getPassword());
            query.setString(4, sub.getInfo().getLogin());
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        }finally {
            try {
                query.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(View.CLOSE_EXCEPTION,e);

            }
        }
        initService(sub);
    }

    /**
     * This method deletes an item from DB
     * @param id is id of item
     * @throws DAOException
     */
    public void delete(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement query = null;
        String s = "UPDATE daotalk.abonents SET deleted=TRUE WHERE contract=?;";
        try {
            connection = JdbcDaoFactory.getConnection();
            query = connection.prepareStatement(s);
            query.setInt(1,id);
            query.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        }finally {
            try {
                query.close();
                connection.close();
            } catch (SQLException e) {
                log.error(View.CLOSE_EXCEPTION, e);
                throw new DAOException(View.CLOSE_EXCEPTION,e);
            }
        }
    }

    /**
     * This method finds an item in DB by login and password
     * @param login is login
     * @param password is password
     * @return true if find else - false
     * @throws DAOException
     */
    public boolean findByLogPas(String login, String password) throws DAOException {
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        String s = "SELECT * FROM daotalk.abonents WHERE login=? and password=?;";
        try {

            connection = JdbcDaoFactory.getConnection();
            log.trace("Connection is opened");
            query = connection.prepareStatement(s);
            log.trace("Statement is created");
            query.setString(1,login);
            query.setString(2,password);
            rs = query.executeQuery();
            log.trace("Result set is executed");
            boolean exist = rs.next();
            return exist;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        }finally {
            try {
                rs.close();
                log.trace("Result set closed");
                query.close();
                log.trace("Statement closed ");
                connection.close();
                log.trace("Connection closed");
            } catch (SQLException e) {
                log.error("Close exception, SQLException:" + e);
                throw new DAOException(View.CLOSE_EXCEPTION,e);
            }
        }
    }

    /**
     * This method finds all items in DB which are not deleted
     * @return list of Subscriber
     * @throws DAOException
     */
    public List<Subscriber> findAll() throws DAOException {
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String s = "SELECT * FROM daotalk.abonents WHERE admin=FALSE AND deleted=FALSE ;";
        try {
            connection = JdbcDaoFactory.getConnection();
            log.trace("Open connection");
            query = connection.prepareStatement(s);
            log.trace("Create statement");
            rs = query.executeQuery();
            log.trace("Execute result set, QUERY: " + s);
            while (rs.next()){
                list.add(getSubByLog(rs.getString(View.QUERY_LOGIN)));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        }finally {
            try {
                rs.close();  //MAKE !=null CHECK
                log.trace("Result set closed");
                query.close();
                log.trace("Statement closed");
                connection.close();
                log.trace("Connection closed");
            } catch (SQLException e) {
                log.error(View.CLOSE_EXCEPTION, e);
                throw new DAOException(View.CLOSE_EXCEPTION,e);
            }
        }
    }

    /**
     * This method finds an item on DB by login
     * @param log is login
     * @return Subscriber
     * @throws DAOException
     */
    public Subscriber getSubByLog(String log) throws DAOException {
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        Subscriber sub = null;
        String s = "SELECT * FROM daotalk.abonents WHERE login=?;";
        try {
            connection=JdbcDaoFactory.getConnection();
            query = connection.prepareStatement(s);
            query.setString(1,log);
            rs = query.executeQuery();
            while(rs.next()){
                sub= new Subscriber();
                sub.setBalance(rs.getDouble(View.QUERY_BALANCE));
                sub.setContract(rs.getInt(View.QUERY_CONTRACT));
                sub.setAdmin(rs.getBoolean(View.QUERY_ADMIN));
                sub.setBlocked(rs.getBoolean(View.QUERY_BLOCKED));
                sub.setCurrentService(getSubsServices(sub.getContract()));
                sub.setInfo(sub.new SubInfo(rs.getString(View.QUERY_S_NAME), rs.getString(View.QUERY_F_NAME), rs.getString(View.QUERY_PASSWORD), log));
            }
            return sub;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        }finally {
            try {
                rs.close();
                query.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(View.CLOSE_EXCEPTION,e);
            }
        }
    }


    /**
     * This method find an item in DB by id
     * @param id is item's id
     * @return Subscriber
     * @throws DAOException
     */
    public Subscriber find(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet rs = null;

        Subscriber sub = new Subscriber();
        String s = "SELECT * FROM daotalk.abonents WHERE contract=? AND deleted=FALSE ;";
        try {

            connection = JdbcDaoFactory.getConnection();
            query = connection.prepareStatement(s);
            query.setInt(1,id);
            rs = query.executeQuery();
            while(rs.next()){
                sub.setBalance(rs.getDouble(View.QUERY_BALANCE));
                sub.setContract(id);
                sub.setAdmin(rs.getBoolean(View.QUERY_ADMIN));
                sub.setBlocked(rs.getBoolean(View.QUERY_BLOCKED));
                sub.setCurrentService(getSubsServices(sub.getContract()));
                sub.setInfo(sub.new SubInfo(rs.getString(View.QUERY_S_NAME), rs.getString(View.QUERY_F_NAME),
                        rs.getString(View.QUERY_PASSWORD), rs.getString(View.QUERY_LOGIN)));
            }
            return sub;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        }finally {
            try {
                rs.close();
                query.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(View.CLOSE_EXCEPTION,e);
            }
        }
    }

    /**
     * This method block an item in DB
     * @param id is item's id
     * @throws DAOException
     */
    public void block(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement query = null;
        String s = "UPDATE daotalk.abonents SET blocked=TRUE WHERE contract=?;";
        try {
            connection = JdbcDaoFactory.getConnection();
            query = connection.prepareStatement(s);
            query.setInt(1,id);
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        }finally {
            try {
                query.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(View.CLOSE_EXCEPTION,e);
            }
        }
    }

    /**
     * This method unlock an item in DB
     * @param id is item's id
     * @throws DAOException
     */
    public void unlock(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement query = null;
        String s = "UPDATE daotalk.abonents SET blocked=FALSE WHERE contract=?;";
        try {
            connection = JdbcDaoFactory.getConnection();
            query = connection.prepareStatement(s);
            query.setInt(1, id);
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        }finally {
            try {
                query.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(View.CLOSE_EXCEPTION,e);
            }
        }
    }

    /**
     * This method update balance of item in DB according Subscriber
     * @param subscriber is Subscriber
     * @throws DAOException
     */
    public void updateBalance(Subscriber subscriber) throws DAOException {
        Connection connection = null;
        PreparedStatement query = null;
        String s = "UPDATE daotalk.abonents SET balance=? WHERE contract=?;";
        try {
            connection = JdbcDaoFactory.getConnection();
            query = connection.prepareStatement(s);
            query.setDouble(1, subscriber.getBalance());
            query.setInt(2, subscriber.getContract());
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        }finally {
            try {
                query.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(View.CLOSE_EXCEPTION,e);
            }
        }
    }

    /**
     * This method update item in DB by Subscriber
     * @param sub Subscriber
     * @throws DAOException
     */
    public void update(Subscriber sub) throws DAOException {
        Connection connection = null;
        PreparedStatement query = null;
        String s = "UPDATE daotalk.abonents SET login=? ,balance=? ,password=? ,first_name=?, second_name=? WHERE contract=?;";
        try {
            connection = JdbcDaoFactory.getConnection();
            query = connection.prepareStatement(s);
            query.setString(1,sub.getInfo().getLogin());
            query.setDouble(2,sub.getBalance());
            query.setString(3,sub.getInfo().getPassword());
            query.setString(4,sub.getInfo().getFirstName());
            query.setString(5,sub.getInfo().getSecondName());
            query.setInt(6,sub.getContract());
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        }finally {
            try {
//                rs.close();
                query.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(View.CLOSE_EXCEPTION,e);
            }
        }
    }

    /**
     * This method deletes services of subs
     * @param id is sub's id
     * @throws DAOException
     */
    private void deleteSubsServices(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement query = null;
        String s2 = "UPDATE daotalk.sub_services SET deleted=TRUE WHERE sub_id=?";

        try {
            connection = JdbcDaoFactory.getConnection();
            query = connection.prepareStatement(s2);
            query.setInt(1, id);
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        }finally {
            try {
                query.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(View.CLOSE_EXCEPTION,e);
            }
        }

    }

    /**
     * This method updates services of subs
     * @param sub is subscriber
     * @throws DAOException
     */
    public void updateSubsServices(Subscriber sub) throws DAOException {
        deleteSubsServices(sub.getContract());
        Connection connection = null;
        PreparedStatement query = null;
        String s2 = "INSERT INTO daotalk.sub_services  (sub_id, service_id) VALUES (?,?)";
        try {
            connection = JdbcDaoFactory.getConnection();
            query = connection.prepareStatement(s2);
            query.setInt(1,sub.getContract());
            for (Service s:sub.getCurrentService()){
                query.setInt(2,s.getId());
                query.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        }finally {
            try {
                query.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(View.CLOSE_EXCEPTION,e);
            }
        }
    }

    /**
     * This method find and write in collection all sub's services
     * @param id is sub's id
     * @return set of services
     * @throws DAOException
     */
    private Set<Service> getSubsServices(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        String s2 = "SELECT * FROM  daotalk.sub_services WHERE sub_id=? AND deleted=FALSE ";

        try {
            connection = JdbcDaoFactory.getConnection();
            query = connection.prepareStatement(s2);
            query.setInt(1, id);
            Set<Service> set = new HashSet<Service>();
            rs=query.executeQuery();
            while (rs.next()){
                set.add(ServService.getInstance().getService(rs.getInt("service_id")));
            }
            return set;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        }finally {
            try {
                query.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(View.CLOSE_EXCEPTION,e);
            }
        }
    }

    /**
     * This method init default service to subscriber when subscriber created
     * @param sub is subscriber
     * @throws DAOException
     */
    private void initService(Subscriber sub) throws DAOException {
        sub = getSubByLog(sub.getInfo().getLogin());
        Connection connection = null;
        PreparedStatement query = null;
        String s2 = "INSERT INTO daotalk.sub_services  (sub_id) VALUES (?)";
        try {
            connection = JdbcDaoFactory.getConnection();
            query = connection.prepareStatement(s2);
            query.setInt(1, sub.getContract());
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        }finally {
            try {
                query.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(View.CLOSE_EXCEPTION,e);
            }
        }
    }


}
