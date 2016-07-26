package main.dao;
import java.util.List;

/**
 * Created by Славик on 26.07.2016.
 */
public interface GenericDao<E> {
    void create( E e);
    void update(E e);
    boolean delete (int id);
    boolean isExist (E e);
    boolean existByLogPas(String login, String password);
    E find(int id);
    List<E> findAll();

}
