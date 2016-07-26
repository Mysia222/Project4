package main.dao;

import main.ent.Subscriber;

/**
 * Created by Славик on 26.07.2016.
 */
public interface SubsDao extends GenericDao {

    Subscriber getSubByLog(String log);

    void update(Subscriber o);

}
