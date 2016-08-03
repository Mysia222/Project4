package dao.jdbc;

import dao.DaoFactory;
import dao.ServicesDao;
import dao.SubsDao;
import resources.View;

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
     * Data source
     */
    private static DataSource ds;

    /**
     * Initial context
     */
    private static InitialContext ic;

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
     * @throws SQLException
     * @throws NamingException
     */
    public static Connection getConnection() throws SQLException, NamingException {
        ic = new InitialContext();
        ds = (DataSource) ic.lookup(View.DB_URL);
        return ds.getConnection();
    }
}
