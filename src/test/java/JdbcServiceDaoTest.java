
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



    @Test
    public void findTest(){
        JdbcServiceDao jdbcServiceDao = new JdbcServiceDao();
        jdbcServiceDao.setTest(true);
        String nameTest = "nameTest";
        Service serviceTest= new Service();
        serviceTest.setName(nameTest);
        try {
            jdbcServiceDao.create(serviceTest);
            serviceTest = jdbcServiceDao.find(nameTest);
            assertEquals(nameTest,serviceTest.getName());
            jdbcServiceDao.delete(serviceTest.getId());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllTest(){
        JdbcServiceDao jdbcServiceDao = new JdbcServiceDao();
        jdbcServiceDao.setTest(true);
        String nameTest1 = "nameTest1";
        String nameTest2 = "nameTest2";
        Service serviceTest1= new Service();
        Service serviceTest2= new Service();
        serviceTest1.setName(nameTest1);
        serviceTest2.setName(nameTest2);

        try {
            jdbcServiceDao.create(serviceTest1);
            jdbcServiceDao.create(serviceTest2);
            serviceTest1 = jdbcServiceDao.find(nameTest1);
            serviceTest2 = jdbcServiceDao.find(nameTest2);
            List list = jdbcServiceDao.findAll();
            assertTrue(list.contains(serviceTest1)&&list.contains(serviceTest2));
            jdbcServiceDao.delete(serviceTest1.getId());
            jdbcServiceDao.delete(serviceTest2.getId());
        } catch (DAOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void createTest(){
    }

    @Test
    public void updateTest(){
//        String testName1 = "updateTest";
//        String testName2 = "NoUpdateTest";
//        Service serviceTest = new Service();
//        serviceTest.setName(testName1);
//        JdbcServiceDao jdbcServiceDao = new JdbcServiceDao();
//        jdbcServiceDao.setTest(true);
//        try {
//            jdbcServiceDao.create(serviceTest);
//            serviceTest=jdbcServiceDao.find(serviceTest.getName());
//            serviceTest.setName(testName2);
//            jdbcServiceDao.update(serviceTest);
//            assertNull(jdbcServiceDao.find(testName2));
//            jdbcServiceDao.delete(serviceTest.getId());
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }

    }
}