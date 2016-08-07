package dao.jdbc;

import dao.DaoFactory;
import dao.ServicesDao;
import dao.SubsDao;
import org.apache.log4j.Logger;
import views.View;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Славик on 26.07.2016.
 */
public class JdbcDaoFactory extends DaoFactory {

    /**
     * Logger
     */
    private static Logger log =  Logger.getLogger(JdbcDaoFactory.class);

    /**
     * Connection to DB
     */
    private static Connection connection;

    /**
     * Default Constructor
     */
    public JdbcDaoFactory() {
    }




    /**
     * Fabric method
     * @return new JdbcServiceDao()
     */
    public ServicesDao createServicesDao() {
        return new JdbcServiceDao();
    }

    /**
     * Fabric method
     * @return new JdbcSubsDao()
     */
    public SubsDao createSubsDao() {
        return new JdbcSubsDao();
    }

    /**
     * This method makes connection with DB
     * @return connection
     */
    static Connection getConnection() {
        if (connection==null) {
            try {
                log.trace("DATA SOURCE");
                DataSource ds = (DataSource) new InitialContext().lookup(View.DB_URL);
                return ds.getConnection();
            } catch (SQLException | NamingException e) {
                log.error(View.LOG_DATA_SOURCE_EXCEPTION, e);
            }
        }else {
            log.trace("SETTED SOURCE");
            return connection;
        }
        return null;
    }

    public static void setConnection(Connection connection) {
        JdbcDaoFactory.connection = connection;
    }
}
