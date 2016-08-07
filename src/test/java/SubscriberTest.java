import ent.Service;
import ent.Subscriber;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Potaychuk Sviatoslav on 01.08.2016.
 */
public class SubscriberTest {
    @Test
    public void getCurrentService() throws Exception {
        Subscriber subscriber = new Subscriber();
        subscriber.setInfo(subscriber.new SubInfo());

        Set<Service> set = new HashSet<Service>();
        set.add(new Service("",0,1,false));
        set.add(new Service("",0,2,false));
        set.add(new Service("",0,3,false));

        subscriber.setCurrentService(set);
        Assert.assertTrue(set.equals(subscriber.getCurrentService()));

    }

    @Test
    public void setCurrentService() throws Exception {
        Subscriber subscriber = new Subscriber();
        subscriber.setInfo(subscriber.new SubInfo());

        Set<Service> set = new HashSet<Service>();
        set.add(new Service("1",1,1,false));
        set.add(new Service("2",2,2,false));
        set.add(new Service("3",2,3,false));

        subscriber.setCurrentService(set);
        Assert.assertTrue(subscriber.getCurrentService().size()==3);
    }

    @Test
    public void isAdmin() throws Exception {
        Subscriber subscriber = new Subscriber();
        subscriber.setInfo(subscriber.new SubInfo());
        Assert.assertTrue(!subscriber.isAdmin());
    }

    @Test
    public void setAdmin() throws Exception {
        Subscriber subscriber = new Subscriber();
        subscriber.setInfo(subscriber.new SubInfo());
        subscriber.setAdmin(true);
        Assert.assertTrue(subscriber.isAdmin());
    }

    @Test
    public void getInfo() throws Exception {
        Subscriber subscriber = new Subscriber();
        Subscriber.SubInfo testSubInfo = subscriber.new SubInfo();
        subscriber.setInfo(testSubInfo);
        Assert.assertEquals(subscriber.getInfo(), testSubInfo);
    }

    @Test
    public void setInfo() throws Exception {
        Subscriber subscriber = new Subscriber();
        Subscriber.SubInfo testSubInfo = subscriber.new SubInfo();
        subscriber.setInfo(testSubInfo);
        Assert.assertEquals(subscriber.getInfo(), testSubInfo);
    }

    @Test
    public void getBalance() throws Exception {
        Subscriber subscriber = new Subscriber();
        Assert.assertTrue(subscriber.getBalance()==0);
    }

    @Test
    public void setBalance() throws Exception {
        Subscriber subscriber = new Subscriber();
        double testBalance = 1;
        subscriber.setBalance(testBalance);
        Assert.assertTrue(subscriber.getBalance()==testBalance);
    }

    @Test
    public void getContract() throws Exception {
        Subscriber subscriber = new Subscriber();
        Assert.assertTrue(subscriber.getContract()==0);
    }

    @Test
    public void setContract() throws Exception {
        Subscriber subscriber = new Subscriber();
        int testContract = 1;
        subscriber.setContract(testContract);
        Assert.assertTrue(subscriber.getContract()==testContract);
    }

    @Test
    public void isBlocked() throws Exception {
        Subscriber subscriber = new Subscriber();
        Assert.assertFalse(subscriber.isBlocked());
    }

    @Test
    public void setBlocked() throws Exception {
        Subscriber subscriber = new Subscriber();
        subscriber.setBlocked(true);
        Assert.assertTrue(subscriber.isBlocked());
    }



}