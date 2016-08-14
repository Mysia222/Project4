package dao.jdbc;

import dao.DAOException;
import dao.ServicesDao;
import ent.Service;
import org.apache.log4j.Logger;
import views.View;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Славик on 26.07.2016.
 */
public class JdbcServiceDao implements ServicesDao {

    /**
     * Logger
     */
    private static Logger log =  Logger.getLogger(JdbcServiceDao.class);

    /**
     * Default constructor
     * @throws SQLException
     * @throws NamingException
     */

    /**
     * This method finds item in DB by id and returns Service object
     * @param id is item's id
     * @return Service object
     * @throws DAOException
     */
    public Service find(int id) throws DAOException {
        log.trace(View.LOG_FIND_SERVICE+id);
        String s = "SELECT * FROM daotalk.tel_service WHERE id=? AND deleted=FALSE ;";
        Service temp = null;
        try(Connection connection = JdbcDaoFactory.getConnection()) {
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            query.setInt(1,id);
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                temp = new Service(rs.getString(View.QUERY_SERVICE_NAME),rs.getDouble(View.QUERY_SERVICE_PRICE), rs.getInt(View.QUERY_SERVICE_ID),rs.getBoolean(View.QUERY_SERVICE_EDIT));
            }
            rs.close();
            query.close();
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
        log.trace(View.LOG_FIND_SERVICE+id+ View.LOG_FINISHED);
        return temp;
    }

    /**
     * This method finds item in DB by name and returns Service object
     * @param name is item's name
     * @return Service object
     * @throws DAOException
     */
    public Service find(String name) throws DAOException {
        log.trace(View.LOG_FIND_SERVICE+name);
        String s = "SELECT * FROM daotalk.tel_service WHERE name=? AND deleted=FALSE ;";
        Service temp = null;
        try(Connection connection = JdbcDaoFactory.getConnection()) {
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            query.setString(1,name);
            ResultSet rs = query.executeQuery();
            while (rs.next()){
                temp = new Service(name,rs.getDouble(View.QUERY_SERVICE_PRICE), rs.getInt(View.QUERY_SERVICE_ID),rs.getBoolean(View.QUERY_SERVICE_EDIT));
            }
            rs.close();
            query.close();
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
        log.trace(View.LOG_FIND_SERVICE+name+ View.LOG_FINISHED);
        return temp;
    }

    /**
     * This method returns collection of Service objects by all items in DB
     * @return
     * @throws DAOException
     */
    public List<Service> findAll() throws DAOException {

        log.trace(View.LOG_FIND_ALL_SERVICE);
        String s = "SELECT * FROM daotalk.tel_service WHERE deleted=FALSE;";
        try(Connection connection = JdbcDaoFactory.getConnection()){
            log.trace(View.LOG_CONNECTED);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(s);
            List<Service> list = new ArrayList<>();
            while (rs.next()){
                list.add(new Service(rs.getString(View.QUERY_SERVICE_NAME),rs.getDouble(View.QUERY_SERVICE_PRICE),rs.getInt(View.QUERY_SERVICE_ID), rs.getBoolean(View.QUERY_SERVICE_EDIT)));
            }
            rs.close();
            statement.close();
            log.trace(View.LOG_FIND_ALL_SERVICE+ View.LOG_FINISHED);
            return list;
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
    }

    /**
     * This method creates item in DB by Service object
     * @param service is Service object
     * @throws DAOException
     */
    public void create(Service service) throws DAOException {
        log.trace(View.LOG_CREATE_SERVICE+service);
        String s = "INSERT INTO daotalk.tel_service  (name, price,edit) VALUES (?,?,?)";
        try(Connection connection = JdbcDaoFactory.getConnection()) {
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            query.setString(1, service.getName());
            query.setDouble(2, service.getPrice());
            query.setBoolean(3, service.isEdit());
            query.execute();
            query.close();
            log.trace(View.LOG_CREATE_SERVICE+service+ View.LOG_FINISHED);
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
    }

    /**
     * This method updates item in DB by Service object
     * @param service is Service object
     * @throws DAOException
     */
    public void update(Service service) throws DAOException {
        log.trace(View.LOG_UPDATE_SERVICE+service);
        String s = "UPDATE daotalk.tel_service SET name=? ,price=? WHERE id=?;";
        try(Connection connection = JdbcDaoFactory.getConnection()) {
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            query.setString(1,service.getName());
            query.setDouble(2,service.getPrice());
            query.setInt(3,service.getId());
            query.execute();
            query.close();
            log.trace(View.LOG_UPDATE_SERVICE+service+ View.LOG_FINISHED);
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
    }

    /**
     * This method deletes item from DB and also removes it from subscribers
     * @param id is id of item
     * @throws DAOException
     */
    public void delete(int id) throws DAOException {
        log.trace(View.LOG_DELETE_SERVICE+id);
        String s = "UPDATE daotalk.tel_service SET deleted=TRUE WHERE id=?;";
        String s2 = " DELETE FROM daotalk.sub_services WHERE service_id=?;";
        try(Connection connection = JdbcDaoFactory.getConnection()) {
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            PreparedStatement query2 = connection.prepareStatement(s2);
            query.setInt(1, id);
            query.execute();
            query2.setInt(1, id);
            query2.execute();
            query.close();
            query2.close();
            log.trace(View.LOG_DELETE_SERVICE+id+ View.LOG_FINISHED);
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION,e);
        }
    }

    /**
     * This method marks item with edit
     * @param id is item id
     */
    public void edit(int id) throws DAOException {
        log.trace(View.LOG_SET_EDIT_TO_SERVICE+id);
        String s = "UPDATE daotalk.tel_service SET edit=TRUE WHERE id=?;";
        try(Connection connection = JdbcDaoFactory.getConnection()) {
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            query.setInt(1, id);
            query.execute();
            query.close();
            log.trace(View.LOG_SET_EDIT_TO_SERVICE+id+View.LOG_FINISHED);
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION, e);
        }
    }

    @Override
    public void unEdit(int id) throws DAOException {
        log.trace(View.LOG_SET_UNEDIT_TO_SERVICE+id);
        String s = "UPDATE daotalk.tel_service SET edit=FALSE WHERE id=?;";
        try(Connection connection = JdbcDaoFactory.getConnection()) {
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            query.setInt(1, id);
            query.execute();
            query.close();
            log.trace(View.LOG_SET_UNEDIT_TO_SERVICE+id+View.LOG_FINISHED);
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION, e);
        }
    }

    @Override
    public boolean nameInUse(String name) throws DAOException {
        log.trace(View.LOG_NAME_IN_USE+name);
        String s = "SELECT * FROM  daotalk.tel_service WHERE name=? AND deleted=FALSE;";
        try(Connection connection = JdbcDaoFactory.getConnection()) {
            log.trace(View.LOG_CONNECTED);
            PreparedStatement query = connection.prepareStatement(s);
            query.setString(1, name);
            ResultSet rs = query.executeQuery();
            boolean temp = rs.next();
            rs.close();
            query.close();
            log.trace(View.LOG_NAME_IN_USE+name+ View.LOG_FINISHED);
            return temp;
        } catch (SQLException e) {
            log.error(View.LOG_EXECUTE_EXCEPTION,e);
            throw new DAOException(View.LOG_EXECUTE_EXCEPTION, e);
        }
    }
}
