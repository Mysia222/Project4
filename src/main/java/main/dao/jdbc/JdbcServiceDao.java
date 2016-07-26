package main.dao.jdbc;

import main.dao.GenericDao;
import main.dao.ServicesDao;
import main.ent.Service;

import javax.naming.NamingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Славик on 26.07.2016.
 */
public class JdbcServiceDao implements ServicesDao {


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

    public Object find(int id) {
        try {
            String s = "SELECT * FROM daotalk.tel_service WHERE id='"+id+"';";
            Statement statement = JdbcDaoFactory.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(s);
            while (rs.next()){
                return new Service(rs.getString("name"),rs.getDouble("price"),id);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List findAll() {
//        ConcurrentMap<Integer,Service> map = new ConcurrentHashMap<Integer, Service>();
        List<Service> list = new ArrayList<Service>();
        try {
            String s = "SELECT * FROM daotalk.tel_service;";
            Statement statement = JdbcDaoFactory.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(s);
            while (rs.next()){
                list.add(new Service(rs.getString("name"),rs.getDouble("price"),rs.getInt("id")));
            }
        } catch (SQLException e) {

            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return list;
    }
}
