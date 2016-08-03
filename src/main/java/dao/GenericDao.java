package dao;
import java.util.List;

/**
 * Created by Славик on 26.07.2016.
 */
public interface GenericDao<E> {

    /**
     * This method creates new item in DB
     * @param e is an object of item
     * @throws DAOException
     */
    void create( E e) throws DAOException;

    /**
     * This method updates new item in DB
     * @param e is an object of item
     * @throws DAOException
     */
    void update(E e) throws DAOException;

    /**
     * This method deletes item from DB
     * @param id is id of item
     * @throws DAOException
     */
    void delete (int id) throws DAOException;

    /**
     * This method search for item in DB
     * @param id is item's id
     * @return object of item
     * @throws DAOException
     */
    E find(int id) throws DAOException;

    /**
     * This method search for all items in DB which are not deleted
     * @return list of item's object
     * @throws DAOException
     */
    List<E> findAll() throws DAOException;

}
