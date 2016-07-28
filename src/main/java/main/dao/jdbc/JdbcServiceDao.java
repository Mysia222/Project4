package main.dao.jdbc;

import main.dao.GenericDao;
import main.dao.ServicesDao;
import main.ent.Service;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Славик on 26.07.2016.
 */
public class JdbcServiceDao implements ServicesDao {

    public Object find(int id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        String s = "SELECT * FROM daotalk.tel_service WHERE id='"+id+"';";

        try {
            connection = JdbcDaoFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(s);
            while (rs.next()){
                return new Service(rs.getString("name"),rs.getDouble("price"),id);
            }
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

    public List findAll() {
//        ConcurrentMap<Integer,Service> map = new ConcurrentHashMap<Integer, Service>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<Service> list = new ArrayList<Service>();
        String s = "SELECT * FROM daotalk.tel_service;";
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

    public void create(Object o) {

    }

    public void update(Object o) {
    }

    public boolean delete(int id) {
        return false;
    }

    public boolean isExist(Object o) {
        return false;
    }

    public boolean existByLogPas(String login, String password) {
        return false;
    }


}
