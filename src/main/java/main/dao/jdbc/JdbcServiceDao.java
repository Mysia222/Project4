package main.dao.jdbc;

import main.dao.DAOException;
import main.dao.ServicesDao;
import main.ent.Service;
import main.resources.View;

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
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        String s = "SELECT * FROM daotalk.tel_service WHERE id=?;";

        try {
            connection = JdbcDaoFactory.getConnection();
            query = connection.prepareStatement(s);
            query.setInt(1,id);
            rs = query.executeQuery();
            while (rs.next()){
                return new Service(rs.getString(View.QUERY_SERVICE_NAME),rs.getDouble(View.QUERY_SERVICE_PRICE), rs.getInt(View.QUERY_SERVICE_ID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION, e);
        }
        finally {
            try {
                rs.close();
                query.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                    throw new DAOException(View.CLOSE_EXCEPTION,e);
            }
        }
        return null;
    }

    /**
     * SThis method returns collection of all Service objects by items in DB
     * @return
     * @throws DAOException
     */
    public List<Service> findAll() throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<Service> list = new ArrayList<Service>();
        String s = "SELECT * FROM daotalk.tel_service WHERE deleted=FALSE ;";
        try {
            connection = JdbcDaoFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(s);
            while (rs.next()){
                list.add(new Service(rs.getString(View.QUERY_SERVICE_NAME),rs.getDouble(View.QUERY_SERVICE_PRICE),rs.getInt(View.QUERY_SERVICE_ID)));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new DAOException(View.EXECUTE_EXCEPTION,e);
        }
        finally {
            try {
                rs.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException(View.CLOSE_EXCEPTION,e);
            }
        }
    }

    /**
     * This method creates item in DB by Service object
     * @param service is Service object
     * @throws DAOException
     */
    public void create(Service service) throws DAOException {
        Connection connection = null;
        PreparedStatement query = null;
        String s = "INSERT INTO daotalk.tel_service  (name, price) VALUES (?,?)";
        try {
            connection = JdbcDaoFactory.getConnection();
            query = connection.prepareStatement(s);
            query.setString(1, service.getName());
            query.setDouble(2, service.getPrice());
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
     * This method updates item in DB by Service object
     * @param service is Service object
     * @throws DAOException
     */
    public void update(Service service) throws DAOException {
        Connection connection = null;
        PreparedStatement query = null;
        String s = "UPDATE daotalk.tel_service SET name=? ,price=? WHERE id=?;";
        try {
            connection = JdbcDaoFactory.getConnection();
            query = connection.prepareStatement(s);
            query.setString(1,service.getName());
            query.setDouble(2,service.getPrice());
            query.setInt(3,service.getId());
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
     * This method deletes item from DB and also removes it from subscribers
     * @param id is id of item
     * @throws DAOException
     */
    public void delete(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement query = null;
        String s = "UPDATE daotalk.tel_service SET deleted=TRUE WHERE id=?;";
        String s2 = "UPDATE daotalk.sub_services SET deleted=TRUE WHERE service_id=?;";
        try {
            connection = JdbcDaoFactory.getConnection();
            query = connection.prepareStatement(s);
            query.setInt(1, id);
            query.execute();
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
}
