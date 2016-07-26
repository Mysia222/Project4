package main.dao;

/**
 * Created by Славик on 26.07.2016.
 */
public abstract class DaoFactory {
    public abstract ServicesDao createServicesDao();
    public abstract SubsDao createSubsDao();
//    public abstract FlowerDao createFlowerDao();

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
