package dao;

import dao.jdbc.JdbcSubsDao;
import org.apache.log4j.Logger;
import views.View;

/**
 * Created by Славик on 26.07.2016.
 */
public abstract class DaoFactory {

    /**
     * Logger
     */
    private static Logger log =  Logger.getLogger(DaoFactory.class);//LogManager.getLogger(JdbcSubsDao.class.getName());


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
    public static DaoFactory getFactory() throws DAOException {
        log.trace(View.LOG_GET_FACTORY);
        try {
            return (DaoFactory) Class.forName(View.JDBS_DAO_FACTORY).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            log.error(View.LOG_DAO_FACTORY_EXCEPTION, e);
            throw new DAOException(View.LOG_DAO_FACTORY_EXCEPTION, e);
        }
    }
}
