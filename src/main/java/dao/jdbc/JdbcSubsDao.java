package dao.jdbc;

import dao.DAOException;
import dao.SubsDao;
import ent.Service;
import ent.Subscriber;
import views.View;
import services.ServService;
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
        log.trace(View.LOG_CREATE_SUBSCRIBER + sub);
        String s = "INSERT INTO daotalk.abonent_info  (first_name,second_name,password,login) VALUES (?,?,?,?)";
        String s2 = "INSERT INTO daotalk.abonents  (contract) VALUES (?)";
        try(Connection connection = JdbcDaoFactory.getConnection()){
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            PreparedStatement query2 = connection.prepareStatement(s2);
            query.setString(1, sub.getInfo().getFirstName());
            query.setString(2, sub.getInfo().getSecondName());
            query.setString(3, sub.getInfo().getPassword());
            query.setString(4, sub.getInfo().getLogin());
            query.execute();
            query2.setInt(1, find(sub.getInfo().getLogin()).getContract());
            
            query2.execute();
            query2.close();
            query.close();
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
        initService(sub);
        log.trace(View.LOG_CREATE_SUBSCRIBER+View.LOG_FINISHED);
    }

    /**
     * This method deletes an item from DB
     * @param id is id of item
     * @throws DAOException
     */
    public void delete(int id) throws DAOException {
        log.trace(View.LOG_DELETE_SUBSCRIBER + id);
        String s = "UPDATE daotalk.abonent_info SET deleted=TRUE WHERE contract=?;";
        try(Connection connection = JdbcDaoFactory.getConnection()){
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            query.setInt(1,id);
            query.executeQuery();
            query.close();
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
        log.trace(View.LOG_DELETE_SUBSCRIBER+id+View.LOG_FINISHED);
    }
    /**
     * This method deletes an item from DB
     * @param login is login
     * @throws DAOException
     */
    @Override
    public void delete(String login) throws DAOException {
        log.trace(View.LOG_DELETE_SUBSCRIBER_LOGIN + login);
        String s = "UPDATE daotalk.abonent_info SET deleted=TRUE WHERE contract=?;";
        try(Connection connection = JdbcDaoFactory.getConnection()){
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            query.setInt(1,find(login).getContract());
            query.execute();
            query.close();
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
        log.trace(View.LOG_DELETE_SUBSCRIBER_LOGIN+login+View.LOG_FINISHED);
    }

    /**
     * This method deletes an item from DB
     * @param login is login
     * @throws DAOException
     */
    @Override
    public void deleteTest(String login) throws DAOException {
        log.trace(View.LOG_DELETE_SUBSCRIBER_LOGIN + login);
        String s = "UPDATE daotalk.abonent_info SET deleted=TRUE WHERE login=?;";
        try(Connection connection = JdbcDaoFactory.getConnection()){
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            query.setString(1,login);
            query.execute();
            query.close();
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
        log.trace(View.LOG_DELETE_SUBSCRIBER_LOGIN+login+View.LOG_FINISHED);
    }

    /**
     * This method finds an item in DB by login and password
     * @param login is login
     * @param password is password
     * @return true if find else - false
     * @throws DAOException
     */
    public boolean exist(String login, String password) throws DAOException {
        log.trace(View.LOG_FIND_BY_LOG_PAS + login +" "+ password);
        String s = "SELECT * FROM daotalk.abonent_info WHERE login=? and password=?;";
        try (Connection connection = JdbcDaoFactory.getConnection()){
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            query.setString(1,login);
            query.setString(2,password);
            ResultSet rs = query.executeQuery();
            boolean exist = rs.next();
            rs.close();
            query.close();
            log.trace(View.LOG_FIND_BY_LOG_PAS + login +" "+ password + View.LOG_FINISHED+" " + View.LOG_RESULT +exist);
            return exist;
        } catch (SQLException e) {
            log.error(View.LOG_FIND_BY_LOG_PAS_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
    }

    /**
     * This method finds all items in DB which are not deleted
     * @return list of Subscriber
     * @throws DAOException
     */
    public List<Subscriber> findAll() throws DAOException {
        log.trace(View.LOG_FIND_ALL_SUBSCRIBERS);
        List list = new ArrayList();
        String s = "SELECT * FROM daotalk.abonent_info WHERE deleted=FALSE AND login!='Admin';";
        try (Connection connection = JdbcDaoFactory.getConnection()){
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                list.add(find(rs.getString(View.QUERY_LOGIN)));
            }
            rs.close();
            log.trace(View.LOG_FIND_ALL_SUBSCRIBERS + View.LOG_FINISHED);
            return list;
        } catch (SQLException e) {
            log.error(View.LOG_FIND_BY_LOG_PAS_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
    }

    /**
     * This method finds an item on DB by login
     * @param login is login
     * @return Subscriber
     * @throws DAOException
     */
    public Subscriber find(String login) throws DAOException {
        log.trace(View.GET_SUB_BY_LOGIN + login);
        Subscriber sub = new Subscriber();
        boolean find = false;
        String s = "SELECT * FROM daotalk.abonent_info WHERE login=?;";
        String s2 = "SELECT * FROM daotalk.abonents WHERE contract=?;";
        try (Connection connection =JdbcDaoFactory.getConnection()){
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            PreparedStatement query2 = connection.prepareStatement(s2);
            query.setString(1,login);

            ResultSet rs = query.executeQuery();

            while(rs.next()){
//                sub.setBalance(rs.getDouble(View.QUERY_BALANCE));
                sub.setContract(rs.getInt(View.QUERY_CONTRACT));
//                sub.setAdmin(rs.getBoolean(View.QUERY_ADMIN));
//                sub.setBlocked(rs.getBoolean(View.QUERY_BLOCKED));
                sub.setInfo(sub.new SubInfo(rs.getString(View.QUERY_F_NAME), rs.getString(View.QUERY_S_NAME), rs.getString(View.QUERY_PASSWORD), login));
                sub.setCurrentService(getSubsServices(sub.getContract()));
                find=true;
            }
            if (find){
                query2.setInt(1,sub.getContract());
                ResultSet rs2 = query2.executeQuery();
                while(rs2.next()){
                    sub.setBalance(rs2.getDouble(View.QUERY_BALANCE));
//                sub.setContract(rs2.getInt(View.QUERY_CONTRACT));
                    sub.setAdmin(rs2.getBoolean(View.QUERY_ADMIN));
                    sub.setBlocked(rs2.getBoolean(View.QUERY_BLOCKED));
//                sub.setInfo(sub.new SubInfo(rs.getString(View.QUERY_F_NAME), rs.getString(View.QUERY_S_NAME), rs.getString(View.QUERY_PASSWORD), login));
//                sub.setCurrentService(getSubsServices(sub.getContract()));
                }
                rs2.close();
                query2.close();
                rs.close();
            }
            query.close();

            log.trace(View.GET_SUB_BY_LOGIN+ View.LOG_FINISHED + View.LOG_RESULT + sub);
            if (find){
                return sub;
            }else {
                return null;
            }
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
    }


    /**
     * This method find an item in DB by id
     * @param id is item's id
     * @return Subscriber
     * @throws DAOException
     */
    public Subscriber find(int id) throws DAOException {
        log.trace(View.LOG_FIND_SUBSCRIBER + id);
        Subscriber sub = new Subscriber();
        String s = "SELECT * FROM daotalk.abonent_info WHERE sub_contract=? AND deleted=FALSE ;";
        String s2 = "SELECT * FROM daotalk.abonents WHERE contract=? AND deleted=FALSE ;";
        try (Connection connection = JdbcDaoFactory.getConnection()){
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            PreparedStatement query2 = connection.prepareStatement(s2);
            query.setInt(1,id);
            query2.setInt(1,id);
            ResultSet rs = query.executeQuery();
            while(rs.next()){
//                sub.setBalance(rs.getDouble(View.QUERY_BALANCE));
                sub.setContract(id);
//                sub.setAdmin(rs.getBoolean(View.QUERY_ADMIN));
//                sub.setBlocked(rs.getBoolean(View.QUERY_BLOCKED));
                sub.setCurrentService(getSubsServices(sub.getContract()));
                sub.setInfo(sub.new SubInfo(rs.getString(View.QUERY_F_NAME), rs.getString(View.QUERY_S_NAME),
                        rs.getString(View.QUERY_PASSWORD), rs.getString(View.QUERY_LOGIN)));
            }
            ResultSet rs2 = query2.executeQuery();
            while(rs2.next()){
                sub.setBalance(rs2.getDouble(View.QUERY_BALANCE));
//                sub.setContract(id);
                sub.setAdmin(rs2.getBoolean(View.QUERY_ADMIN));
                sub.setBlocked(rs2.getBoolean(View.QUERY_BLOCKED));
//                sub.setCurrentService(getSubsServices(sub.getContract()));
//                sub.setInfo(sub.new SubInfo(rs.getString(View.QUERY_F_NAME), rs.getString(View.QUERY_S_NAME),
//                        rs.getString(View.QUERY_PASSWORD), rs.getString(View.QUERY_LOGIN)));
            }
            rs.close();
            query.close();
            rs2.close();
            query2.close();
            log.trace(View.LOG_FIND_SUBSCRIBER + id + " " + View.LOG_FINISHED);
            return sub;
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
    }

    /**
     * This method block an item in DB
     * @param id is item's id
     * @throws DAOException
     */
    public void block(int id) throws DAOException {
        log.trace(View.LOG_LOCK_SUBSCRIBER + id);
        String s = "UPDATE daotalk.abonents SET blocked=TRUE WHERE contract=?;";
        try (Connection connection = JdbcDaoFactory.getConnection()){
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            query.setInt(1,id);
            query.execute();
            query.close();
            log.trace(View.LOG_LOCK_SUBSCRIBER + id + View.LOG_FINISHED);
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
    }

    /**
     * This method unlock an item in DB
     * @param id is item's id
     * @throws DAOException
     */
    public void unlock(int id) throws DAOException {
        log.trace(View.LOG_UNLOCK_SUBSCRIBER + id);
        String s = "UPDATE daotalk.abonents SET blocked=FALSE WHERE contract=?;";
        try (Connection connection = JdbcDaoFactory.getConnection()){
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            query.setInt(1, id);
            query.execute();
            query.close();
            log.trace(View.LOG_UNLOCK_SUBSCRIBER + id + View.LOG_FINISHED);
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
    }

    /**
     * This method update balance of item in DB according Subscriber
     * @param subscriber is Subscriber
     * @throws DAOException
     */
    public void updateBalance(Subscriber subscriber) throws DAOException {
        log.trace(View.LOG_UPDATE_SUBSCRIBERS_BALANCE + subscriber);
        String s = "UPDATE daotalk.abonents SET balance=? WHERE contract=?;";
        try(Connection connection = JdbcDaoFactory.getConnection()){
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            query.setDouble(1, subscriber.getBalance());
            query.setInt(2, subscriber.getContract());
            query.execute();
            query.close();
            log.trace(View.LOG_UPDATE_SUBSCRIBERS_BALANCE + subscriber + View.LOG_FINISHED);
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
    }

    /**
     * This method update item in DB by Subscriber
     * @param sub Subscriber
     * @throws DAOException
     */
    public void update(Subscriber sub) throws DAOException {
        log.trace(View.LOG_UPDATE_SUBSCRIBER + sub);
        String s = "UPDATE daotalk.abonent_info SET login=? ,password=? ,first_name=?, second_name=? WHERE sub_contract=?;";
        String s2 = "UPDATE daotalk.abonents SET balance=?  WHERE contract=?;";
        try(Connection connection = JdbcDaoFactory.getConnection()){
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            PreparedStatement query2 = connection.prepareStatement(s2);
            query.setString(1,sub.getInfo().getLogin());
            query.setString(2,sub.getInfo().getPassword());
            query.setString(3,sub.getInfo().getFirstName());
            query.setString(4,sub.getInfo().getSecondName());
            query.setInt(5,sub.getContract());
            query.execute();

            query2.setDouble(1,sub.getBalance());
            query2.setDouble(2,sub.getContract());
            query2.execute();
            query.close();
            log.trace(View.LOG_UPDATE_SUBSCRIBER + sub+ View.LOG_FINISHED);
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
    }

    /**
     * This method deletes services of subs
     * @param id is sub's id
     * @throws DAOException
     */
    private void deleteSubsServices(int id) throws DAOException {
        log.trace(View.LOG_DELETE_SUBSCRIBERS_SERVICES + id);
        String s = "DELETE FROM daotalk.sub_services WHERE sub_id=?";
        try(Connection connection = JdbcDaoFactory.getConnection()){
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            query.setInt(1, id);
            query.execute();
            query.close();
            log.trace(View.LOG_DELETE_SUBSCRIBERS_SERVICES + id+ View.LOG_FINISHED);
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
    }

    /**
     * This method updates services of subs
     * @param sub is subscriber
     * @throws DAOException
     */
    public void updateSubsServices(Subscriber sub) throws DAOException {
        log.trace(View.LOG_UPDATE_SUBSCRIBERS_SERVICES + sub);
        deleteSubsServices(sub.getContract());
        String s = "INSERT INTO daotalk.sub_services  (sub_id, service_id) VALUES (?,?)";
        try(Connection connection = JdbcDaoFactory.getConnection()){
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            query.setInt(1,sub.getContract());
            for (Service service:sub.getCurrentService()){
                query.setInt(2,service.getId());
                query.execute();
            }
            query.close();
            log.trace(View.LOG_UPDATE_SUBSCRIBERS_SERVICES + sub+ View.LOG_FINISHED);
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
    }



    /**
     * This method find and write in collection all sub's services
     * @param id is sub's id
     * @return set of services
     * @throws DAOException
     */
    private Set<Service> getSubsServices(int id) throws DAOException {

        log.trace(View.LOG_GET_SUBS_SERVICES + id);
        String s = "SELECT * FROM  daotalk.sub_services WHERE sub_id=?";
        try(Connection connection = JdbcDaoFactory.getConnection()){
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            query.setInt(1,id);
            Set<Service> set = new HashSet<>();
            ResultSet rs=query.executeQuery();
            while (rs.next()){
                set.add(ServService.getInstance().getService(rs.getInt(View.QUERY_SUB_SERVICE_ID)));
            }
            query.close();
            log.trace(View.LOG_GET_SUBS_SERVICES + id+ View.LOG_FINISHED);
            return set;
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
    }

    /**
     * This method init default service to subscriber when subscriber created
     * @param sub is subscriber
     * @throws DAOException
     */
    private void initService(Subscriber sub) throws DAOException {
        log.trace(View.LOG_INIT_SERVICE + sub);
        sub = find(sub.getInfo().getLogin());
        String s = "INSERT INTO daotalk.sub_services  (sub_id) VALUES (?)";
        try(Connection connection = JdbcDaoFactory.getConnection()){
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            query.setInt(1, sub.getContract());
            query.execute();
            log.trace(View.LOG_INIT_SERVICE + sub+ View.LOG_FINISHED);
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
    }
}
