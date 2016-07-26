package main.dao.jdbc;

import main.dao.DaoFactory;
//import main.dao.JDBCRunner;
import main.dao.GenericDao;
import main.dao.SubsDao;
import main.ent.Subscriber;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Славик on 26.07.2016.
 */
public class JdbcSubsDao implements SubsDao {
    private static Logger log = LogManager.getLogger(JdbcSubsDao.class.getName());

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
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        String s = "SELECT * FROM daotalk.abonents WHERE login='"+login+"' and password='"+password+"';";
        try {
            log.trace("Open connection");
            System.out.println("SDASD");
            connection = JdbcDaoFactory.getConnection();
            log.trace("Create statement");
            statement = connection.createStatement();
            log.trace("Execute result set");
            rs = statement.executeQuery(s);
            boolean exist = rs.next();
            return exist;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                log.trace("Result set closed");
                statement.close();
                log.trace("Statement closed");
                connection.close();
                log.trace("Connection closed");
            } catch (SQLException e) {
                log.error("Close exception, SQLException:" + e);
                e.printStackTrace();
            }
        }
        return false;
    }

    public Object find(int id) {
        return null;
    }

    public List findAll() {
        return null;
    }


    public Subscriber getSubByLog(String log) {
        try {
            Subscriber sub = new Subscriber();
            Statement statement = JdbcDaoFactory.getConnection().createStatement();
            String s = "SELECT * FROM daotalk.abonents WHERE login='"+log+"';";
            ResultSet rs = statement.executeQuery(s);
            while(rs.next()){
                sub.setBalance(rs.getDouble("balance"));
                sub.setContract(rs.getInt("contract"));
                sub.setCurrentService(rs.getString("current_service"));
                sub.setInfo(sub.new SubInfo(rs.getString("second_name"), rs.getString("first_name"), rs.getString("password"), log));
            }
            return sub;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Subscriber sub) {
        Statement statement = null;
        try {
            statement = JdbcDaoFactory.getConnection().createStatement();
            String s = "UPDATE daotalk.abonents SET login='"+sub.getInfo().getLogin()+"' ,current_service='"+sub.getCurrentService()+
                "' ,balance='"+sub.getBalance()+"' ,password='"+sub.getInfo().getPassword()+"' ,first_name='"+sub.getInfo().getFirstName()+
                "', second_name='"+sub.getInfo().getSecondName()+"' WHERE contract='"+sub.getContract()+"';";
        statement.execute(s);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
