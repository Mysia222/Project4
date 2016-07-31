package main.dao;

import main.ent.Subscriber;

import java.util.List;

/**
 * Created by Славик on 26.07.2016.
 */
public interface SubsDao extends GenericDao<Subscriber> {

    boolean findByLogPas(String login, String password);
    List getDebtors();
    Subscriber find(int id);
    Subscriber getSubByLog(String log);
    void block(int id);
    void unlock(int id);
    void updateBalance(Subscriber subscriber);
    void updateSubsServices(Subscriber sub);
}
