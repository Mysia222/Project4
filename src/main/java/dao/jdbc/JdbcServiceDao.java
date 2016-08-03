package dao.jdbc;

import dao.DAOException;
import dao.ServicesDao;
import ent.Service;
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
     * This method finds item in DB by id returns Service object
     * @param id is item's id
     * @return Service object
     * @throws DAOException
     */
    public Service find(int id) throws DAOException {
        String s = "SELECT * FROM daotalk.tel_service WHERE id=?;";
        try(Connection connection = JdbcDaoFactory.getConnection();
            PreparedStatement query = connection.prepareStatement(s)){
            query.setInt(1,id);
            ResultSet rs = query.executeQuery();

            while (rs.next()){
               Service temp = new Service(rs.getString(View.QUERY_SERVICE_NAME),rs.getDouble(View.QUERY_SERVICE_PRICE), rs.getInt(View.QUERY_SERVICE_ID));
               rs.close();
               return temp;
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        }
        return null;
    }

    /**
     * This method returns collection of all Service objects by items in DB
     * @return
     * @throws DAOException
     */
    public List<Service> findAll() throws DAOException {
        String s = "SELECT * FROM daotalk.tel_service WHERE deleted=FALSE ;";
        try(Statement statement = JdbcDaoFactory.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(s);
            List<Service> list = new ArrayList<>();
            while (rs.next()){
                list.add(new Service(rs.getString(View.QUERY_SERVICE_NAME),rs.getDouble(View.QUERY_SERVICE_PRICE),rs.getInt(View.QUERY_SERVICE_ID)));
            }
            return list;
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        }
    }

    /**
     * This method creates item in DB by Service object
     * @param service is Service object
     * @throws DAOException
     */
    public void create(Service service) throws DAOException {
        String s = "INSERT INTO daotalk.tel_service  (name, price) VALUES (?,?)";
        try(PreparedStatement query = JdbcDaoFactory.getConnection().prepareStatement(s)) {
            query.setString(1, service.getName());
            query.setDouble(2, service.getPrice());
            query.execute();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        }
    }

    /**
     * This method updates item in DB by Service object
     * @param service is Service object
     * @throws DAOException
     */
    public void update(Service service) throws DAOException {
        String s = "UPDATE daotalk.tel_service SET name=? ,price=? WHERE id=?;";
        try(PreparedStatement query = JdbcDaoFactory.getConnection().prepareStatement(s)) {
            query.setString(1,service.getName());
            query.setDouble(2,service.getPrice());
            query.setInt(3,service.getId());
            query.execute();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        }
    }

    /**
     * This method deletes item from DB and also removes it from subscribers
     * @param id is id of item
     * @throws DAOException
     */
    public void delete(int id) throws DAOException {
        String s = "UPDATE daotalk.tel_service SET deleted=TRUE WHERE id=?;";
        String s2 = "UPDATE daotalk.sub_services SET deleted=TRUE WHERE service_id=?;";
        try(PreparedStatement query = JdbcDaoFactory.getConnection().prepareStatement(s);
            PreparedStatement query2 = JdbcDaoFactory.getConnection().prepareStatement(s2)) {
            query.setInt(1, id);
            query.execute();
            query2.setInt(1, id);
            query.execute();

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        }
    }
}
