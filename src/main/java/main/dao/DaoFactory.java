package main.dao;

/**
 * Created by Славик on 26.07.2016.
 */
public abstract class DaoFactory {

    /**
     * This fabric method creates and returns ServicesDao
     * @return ServicesDao
     */
    public abstract ServicesDao createServicesDao();

    /**
     * This fabric method creates and returns SubsDao
     * @return SubsDao
     */
    public abstract SubsDao createSubsDao();

    /**
     * This method return factory
     * @return DaoFactory
     */
    public static DaoFactory getFactory(){
        try {
            return (DaoFactory) Class.forName("main.dao.jdbc.JdbcDaoFactory").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
