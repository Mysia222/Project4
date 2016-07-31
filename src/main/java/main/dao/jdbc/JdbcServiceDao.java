package main.dao.jdbc;

import main.dao.GenericDao;
import main.dao.ServicesDao;
import main.ent.Service;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Славик on 26.07.2016.
 */
public class JdbcServiceDao implements ServicesDao {

    public Service find(int id) {
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
                return new Service(rs.getString("name"),rs.getDouble("price"), rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        finally {
            try {
                rs.close();
                query.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Service> findAll() {
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
                list.add(new Service(rs.getString("name"),rs.getDouble("price"),rs.getInt("id")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        finally {
            try {
                rs.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void create(Service service) {
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
        } catch (NamingException e) {
            e.printStackTrace();
        }finally {
            try {
                query.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Service service) {
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
        } catch (NamingException e) {
            e.printStackTrace();
        }finally {
            try {
                query.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int id) {
        Connection connection = null;
        PreparedStatement query = null;
        String s = "UPDATE daotalk.tel_service SET deleted=TRUE WHERE id=?;";
        try {
            connection = JdbcDaoFactory.getConnection();
            query = connection.prepareStatement(s);
            query.setInt(1, id);
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }finally {
            try {
                query.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean isExist(Service service) {
        Connection connection = null;
        PreparedStatement query = null;
        ResultSet rs = null;
        boolean res = false;
        String s = "SELECT * FROM  daotalk.tel_service WHERE id=?;";
        try {
            connection = JdbcDaoFactory.getConnection();
            query = connection.prepareStatement(s);
            query.setInt(1, service.getId());
            rs = query.executeQuery();
            res = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }finally {
            try {
                query.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }



}
