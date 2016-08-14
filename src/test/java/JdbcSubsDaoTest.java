import dao.DAOException;
import dao.jdbc.JdbcDaoFactory;
import dao.jdbc.JdbcServiceDao;
import dao.jdbc.JdbcSubsDao;
import ent.Service;
import ent.Subscriber;
import org.junit.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by Славик on 14.08.2016.
 */
public class JdbcSubsDaoTest {

    void setTestConnection(){
        try {
            JdbcDaoFactory.setConnection(DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void create() throws Exception {
        int testContract = -1;
        Subscriber subTest= new Subscriber();
        subTest.setInfo(subTest.new SubInfo("test", "test", "test", "test"));
        subTest.setContract(testContract);
        JdbcSubsDao dao = new JdbcSubsDao();
        try{
            setTestConnection();
            dao.create(subTest);
            setTestConnection();
            subTest = dao.find(subTest.getContract());
            assertTrue(testContract==subTest.getContract());
            setTestConnection();
            dao.delete(subTest.getContract());
        } catch (DAOException e) {
            e.printStackTrace();
        }finally {
            try {
                setTestConnection();
                dao.delete(subTest.getInfo().getLogin());
            }catch (DAOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void delete() throws Exception {
        int testContract = -1;
        Subscriber subTest= new Subscriber();
        subTest.setInfo(subTest.new SubInfo("test", "test", "test", "test"));
        subTest.setContract(testContract);
        JdbcSubsDao dao = new JdbcSubsDao();
        try{
            setTestConnection();
            dao.create(subTest);
            setTestConnection();
            dao.delete(subTest.getContract());
            setTestConnection();
            assertNull(dao.find(subTest.getContract()));
        } catch (DAOException e) {
            e.printStackTrace();
        }finally {
            try {
                setTestConnection();
                dao.delete(subTest.getInfo().getLogin());
            }catch (DAOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void exist() throws Exception {
        int testContract = -1;
        Subscriber subTest= new Subscriber();
        subTest.setInfo(subTest.new SubInfo("test", "test", "test", "test"));
        subTest.setContract(testContract);
        JdbcSubsDao dao = new JdbcSubsDao();
        try{
            setTestConnection();
            dao.create(subTest);
            setTestConnection();
            assertTrue(dao.exist(subTest.getInfo().getLogin(), subTest.getInfo().getPassword()));
            setTestConnection();
            dao.delete(subTest.getContract());
        } catch (DAOException e) {
            e.printStackTrace();
        }finally {
            try {
                setTestConnection();
                dao.delete(subTest.getInfo().getLogin());
            }catch (DAOException e) {
                e.printStackTrace();
            }
        }
    }

}