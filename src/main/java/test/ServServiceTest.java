package test;

import ent.Service;
import services.ServService;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Potaychuk Sviatoslav on 01.08.2016.
 */
public class ServServiceTest {
    ServService servService = ServService.getInstance();
    static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Test
    public void getInstance() throws Exception {
        Assert.assertTrue(servService!=null);
    }

    @Test
    public void getServiceList() throws Exception {
        List<Service> listTest = servService.getServiceList();
        Assert.assertTrue(listTest!=null);
    }

    @Test
    public void getService() throws Exception {
        int testID = 0;
        Service serviceTest = servService.getService(testID);
        Assert.assertTrue(serviceTest.getId()==testID);
    }

    @Test
    public void create() throws Exception {
//        double testPrice = -1;
//        int testID = -1;
//        Service testService = new Service("test",testPrice,testID);
//        servService.create(testService);
//        Service temp = servService.getService(testID);
//        Assert.assertEquals(temp,testService);
//        servService.delete(testID);
    }

    @Test
    public void delete() throws Exception {
//        double testPrice = -1;
//        int testID = -1;
//        Service testService = new Service("test",testPrice,testID);
//        servService.create(testService);
//        servService.delete(testID);
//        Assert.assertNull(servService.getService(testID));
    }

    @Test
    public void update() throws Exception {
//        double testPrice = -1;
//        int testID = -1;
//        Service testService = new Service("test",testPrice,testID);
//        servService.create(testService);
//        testService.setPrice(2);
//        Service temp = servService.getService(testID);
//        Assert.assertTrue(2==temp.getPrice());
//        servService.delete(testID);
    }

}