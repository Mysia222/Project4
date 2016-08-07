package dao;

import ent.Subscriber;

/**
 * Created by Potaychuk Sviatoslav on 26.07.2016.
 */
public interface SubsDao extends GenericDao<Subscriber> {

    /**
     * This method looking for item in DB with same login and password
     * @param login is login
     * @param password is password
     * @return result of searching
     * @throws DAOException
     */
    boolean exist(String login, String password) throws DAOException;

    /**
     * This method find item in DB
     * @param id is item's id
     * @return Subscriber
     * @throws DAOException
     */
    Subscriber find(int id) throws DAOException;

    /**
     * This method returns item from DB with certain login
     * @param log is login
     * @return Subscriber
     * @throws DAOException
     */
    Subscriber find(String log) throws DAOException;

    /**
     * This method blocks item
     * @param id is item's id
     * @throws DAOException
     */
    void block(int id) throws DAOException;

    /**
     * This method unlocks item
     * @param id is item's id
     * @throws DAOException
     */
    void unlock(int id) throws DAOException;

    /**
     * This method updates item of subscriber
     * @param subscriber is subscriber
     * @throws DAOException
     */
    void updateBalance(Subscriber subscriber) throws DAOException;

    /**
     * This method updates subscriber's list of services
     * @param sub is subscriber
     * @throws DAOException
     */
    void updateSubsServices(Subscriber sub) throws DAOException;
}
