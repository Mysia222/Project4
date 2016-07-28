package main.dao.jdbc;

//import main.dao.JDBCRunner;
import main.dao.SubsDao;
import main.ent.Subscriber;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Славик on 26.07.2016.
 */
public class JdbcSubsDao implements SubsDao {
    private static Logger log =  Logger.getLogger(JdbcSubsDao.class);//LogManager.getLogger(JdbcSubsDao.class.getName());

    public void create(Object o) {
//        Subscriber subscriber = (Subscriber)o;
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet rs = null;
//        String s = "SELECT * FROM daotalk.abonents WHERE login='"+login+"' and password='"+password+"';";
//        try {
//            log.trace("Open connection");
//            connection = JdbcDaoFactory.getConnection();
//            log.trace("Create statement");
//            statement = connection.createStatement();
//            log.trace("Execute result set");
//            rs = statement.executeQuery(s);
//            boolean exist = rs.next();
//            return exist;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                rs.close();
//                log.trace("Result set closed");
//                statement.close();
//                log.trace("Statement closed");
//                connection.close();
//                log.trace("Connection closed");
//            } catch (SQLException e) {
//                log.error("Close exception, SQLException:" + e);
//                e.printStackTrace();
//            }
//        }
//        return false;


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


    private void makeResultSet(String s){

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
    }

    public List findAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String s = "SELECT * FROM daotalk.abonents WHERE admin=FALSE ;";
        try {
            connection = JdbcDaoFactory.getConnection();
            log.trace("Open connection");
            statement = connection.createStatement();
            log.trace("Create statement");
            rs = statement.executeQuery(s);
            log.trace("Execute result set, QUERY: " + s);
            while (rs.next()){
                list.add(getSubByLog(rs.getString("login")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();  //MAKE !=null CHECK
                log.trace("Result set closed");
                statement.close();
                log.trace("Statement closed");
                connection.close();
                log.trace("Connection closed");
            } catch (SQLException e) {
                log.error("Close exception, SQLException:" + e);
            }
        }
        return null;
    }

    public Subscriber getSubByLog(String log) {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        Subscriber sub = new Subscriber();
        String s = "SELECT * FROM daotalk.abonents WHERE login='"+log+"';";
        try {
            connection=JdbcDaoFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(s);
            while(rs.next()){
                sub.setBalance(rs.getDouble("balance"));
                sub.setContract(rs.getInt("contract"));
                sub.setAdmin(rs.getBoolean("admin"));
                sub.setBlocked(rs.getBoolean("blocked"));
                sub.setCurrentService(rs.getString("current_service"));
                sub.setInfo(sub.new SubInfo(rs.getString("second_name"), rs.getString("first_name"), rs.getString("password"), log));
            }
            return sub;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }finally {
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

    public List getDebtors() {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String s = "SELECT * FROM daotalk.abonents WHERE balance<0 ;";
        try {
            connection = JdbcDaoFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(s);
            while (rs.next()){
                list.add(getSubByLog(rs.getString("login")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }finally {
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

    public Object find(int id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        Subscriber sub = new Subscriber();
        String s = "SELECT * FROM daotalk.abonents WHERE contract='"+id+"';";
        try {

            connection = JdbcDaoFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(s);
            while(rs.next()){
                sub.setBalance(rs.getDouble("balance"));
                sub.setContract(id);
                sub.setAdmin(rs.getBoolean("admin"));
                sub.setBlocked(rs.getBoolean("blocked"));
                sub.setCurrentService(rs.getString("current_service"));
                sub.setInfo(sub.new SubInfo(rs.getString("second_name"), rs.getString("first_name"), rs.getString("password"), rs.getString("login")));
            }
            return sub;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }finally {
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

    public void blockId(int id){
        Connection connection = null;
        Statement statement = null;
//        ResultSet rs = null;
        String s = "UPDATE daotalk.abonents SET blocked=TRUE WHERE contract='" +id+"';";
        try {
            connection = JdbcDaoFactory.getConnection();
            statement = connection.createStatement();
            statement.execute(s);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }finally {
            try {
//                rs.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void unlock(int contract) {
        Connection connection = null;
        Statement statement = null;
//        ResultSet rs = null;
        String s = "UPDATE daotalk.abonents SET blocked=FALSE WHERE contract='" +contract+"';";
        try {
            connection = JdbcDaoFactory.getConnection();
            statement = connection.createStatement();
            statement.execute(s);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }finally {
            try {
//                rs.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void update(Object o) {
        Connection connection = null;
        Statement statement = null;
//        ResultSet rs = null;
        Subscriber sub = (Subscriber)o;
        String s = "UPDATE daotalk.abonents SET login='"+sub.getInfo().getLogin()+"' ,current_service='"+sub.getCurrentService()+
                "' ,balance='"+sub.getBalance()+"' ,password='"+sub.getInfo().getPassword()+"' ,first_name='"+sub.getInfo().getFirstName()+
                "', second_name='"+sub.getInfo().getSecondName()+"' WHERE contract='"+sub.getContract()+"';";
        try {
            connection = JdbcDaoFactory.getConnection();
            statement = connection.createStatement();
            statement.execute(s);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }finally {
            try {
//                rs.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
