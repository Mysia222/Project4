package main.dao;

import main.ent.Subscriber;

import java.util.List;

/**
 * Created by Славик on 26.07.2016.
 */
public interface SubsDao extends GenericDao<Subscriber> {

    Subscriber getSubByLog(String log);

    List getDebtors();

    Subscriber find(int contract);
    public void blockId(int id);

    void unlock(int contract);
}
