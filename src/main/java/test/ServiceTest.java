package test;

import ent.Service;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Potaychuk Sviatoslav on 01.08.2016.
 */
public class ServiceTest {


    @Test
    public void getId() throws Exception {
        Service service = new Service();
        assertTrue(0==service.getId());

    }

    @org.junit.Test
    public void setId() throws Exception {
        Service service = new Service();
        int testId = 1;
        service.setId(testId);
        assertTrue(testId==service.getId());
    }

    @org.junit.Test
    public void getName() throws Exception {
        Service service = new Service();
        String testName = "testName";
        service.setName(testName);
        assertTrue(testName.equals(service.getName()));
    }

    @org.junit.Test
    public void setName() throws Exception {
        Service service = new Service();
        String testName = "testName";
        service.setName(testName);
        assertTrue(testName.equals(service.getName()));
    }

    @org.junit.Test
    public void getPrice() throws Exception {
        Service service = new Service();
        assertTrue(0==service.getPrice());
    }

    @org.junit.Test
    public void setPrice() throws Exception {
        Service service = new Service();
        double testPrice = 1;
        service.setPrice(testPrice);
        assertTrue(testPrice==(service.getPrice()));
    }

}