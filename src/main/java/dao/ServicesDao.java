package dao;

import ent.Service;

/**
 * Created by Славик on 26.07.2016.
 */
public interface ServicesDao extends GenericDao<Service> {
    void edit(int id, boolean edit) throws DAOException;
    Service find(String name) throws DAOException;
    boolean nameInUse(String name) throws DAOException;
}
