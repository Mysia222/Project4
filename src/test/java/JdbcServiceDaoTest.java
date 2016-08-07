
import dao.DAOException;
import dao.jdbc.JdbcDaoFactory;
import dao.jdbc.JdbcServiceDao;
import ent.Service;
import org.junit.Assert;
import org.junit.Test;
import views.View;

import javax.naming.NamingException;
import javax.validation.constraints.AssertTrue;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Potaychuk Sviatoslav on 01.08.2016.
 */
public class JdbcServiceDaoTest {

    void setTestConnection(){
        try {
            JdbcDaoFactory.setConnection(DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void findTest() {
        try {
            String nameTest = "nameTest";
            Service serviceTest= new Service();
            serviceTest.setName(nameTest);
            JdbcServiceDao serviceDao = new JdbcServiceDao();
            setTestConnection();
            serviceDao.create(serviceTest);
            setTestConnection();
            serviceTest = serviceDao.find(nameTest);
            assertEquals(nameTest,serviceTest.getName());
            setTestConnection();
            serviceDao.delete(serviceTest.getId());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllTest(){
        JdbcServiceDao jdbcServiceDao = new JdbcServiceDao();
        String nameTest1 = "nameTest1";
        String nameTest2 = "nameTest2";
        Service serviceTest1= new Service();
        Service serviceTest2= new Service();
        serviceTest1.setName(nameTest1);
        serviceTest2.setName(nameTest2);
        try {
            setTestConnection();
            jdbcServiceDao.create(serviceTest1);
            setTestConnection();
            jdbcServiceDao.create(serviceTest2);
            setTestConnection();
            serviceTest1 = jdbcServiceDao.find(nameTest1);
            setTestConnection();
            serviceTest2 = jdbcServiceDao.find(nameTest2);
            setTestConnection();
            List list = jdbcServiceDao.findAll();
            assertTrue(list.contains(serviceTest1)&&list.contains(serviceTest2));
            setTestConnection();
            jdbcServiceDao.delete(serviceTest1.getId());
            setTestConnection();
            jdbcServiceDao.delete(serviceTest2.getId());
        } catch (DAOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void createTest(){
        try{
            String nameTest = "createTest";
            Service serviceTest= new Service();
            serviceTest.setName(nameTest);
            JdbcServiceDao serviceDao = new JdbcServiceDao();
            setTestConnection();
            serviceDao.create(serviceTest);
            setTestConnection();
            serviceTest = serviceDao.find(nameTest);
            assertEquals(nameTest,serviceTest.getName());
            setTestConnection();
            serviceDao.delete(serviceTest.getId());
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void updateTest(){
        try{
            String nameTest1 = "updateTest1";
            String nameTest2 = "updateTest2";
            Service serviceTest= new Service();
            serviceTest.setName(nameTest1);
            JdbcServiceDao serviceDao = new JdbcServiceDao();
            setTestConnection();
            serviceDao.create(serviceTest);
            setTestConnection();
            serviceTest = serviceDao.find(nameTest1);
            serviceTest.setName(nameTest2);
            setTestConnection();
            serviceDao.update(serviceTest);
            setTestConnection();
            Service serviceTestUpdate = serviceDao.find(nameTest2);
            assertTrue(serviceTestUpdate.getName().equals(serviceTest.getName()) &&serviceTestUpdate.getPrice()==serviceTest.getPrice());
            setTestConnection();
            serviceDao.delete(serviceTest.getId());
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void deleteTest(){
        try{
            String nameTest = "deleteTest";
            Service serviceTest= new Service();
            serviceTest.setName(nameTest);
            JdbcServiceDao serviceDao = new JdbcServiceDao();
            setTestConnection();
            serviceDao.create(serviceTest);
            setTestConnection();
            serviceTest = serviceDao.find(serviceTest.getName());
            setTestConnection();
            serviceDao.delete(serviceTest.getId());
            setTestConnection();
            serviceTest = serviceDao.find(serviceTest.getName());
            assertNull(serviceTest);
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void editTest(){
        try{
            String nameTest = "editTest";
            Service serviceTest= new Service();
            serviceTest.setName(nameTest);
            JdbcServiceDao serviceDao = new JdbcServiceDao();
            setTestConnection();
            serviceDao.create(serviceTest);
            setTestConnection();
            serviceTest = serviceDao.find(serviceTest.getName());
            setTestConnection();
            serviceDao.edit(serviceTest.getId());
            setTestConnection();
            serviceTest = serviceDao.find(serviceTest.getName());
            assertTrue(serviceTest.isEdit());
            setTestConnection();
            serviceDao.delete(serviceTest.getId());
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }



    @Test
    public void nameInUseTest(){
        try{
            String nameTest = "nameInUseTest";
            Service serviceTest= new Service();
            serviceTest.setName(nameTest);
            JdbcServiceDao serviceDao = new JdbcServiceDao();
            setTestConnection();
            serviceDao.create(serviceTest);
            setTestConnection();
            assertTrue(serviceDao.nameInUse(serviceTest.getName()));
            setTestConnection();
            serviceTest=serviceDao.find(serviceTest.getName());
            setTestConnection();
            serviceDao.delete(serviceTest.getId());
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

}