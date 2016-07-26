package main.dao.jdbc;

import main.dao.DaoFactory;
import main.dao.ServicesDao;
import main.dao.SubsDao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Славик on 26.07.2016.
 */
public class JdbcDaoFactory extends DaoFactory {

    private static DataSource ds;
    private static InitialContext ic;

    public JdbcDaoFactory() {
//        try {
//            InitialContext ic = new InitialContext();
//            ds = (DataSource) ic.lookup("java:comp/env/dao/mysql");
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
    }


    public ServicesDao createServicesDao() {
        return new JdbcServiceDao();
    }

    public SubsDao createSubsDao() {
        return new JdbcSubsDao();
    }


    public static Connection getConnection() throws SQLException, NamingException {
        ic = new InitialContext();
        ds = (DataSource) ic.lookup("java:comp/env/jdbc/mysql");
        return ds.getConnection();
    }
}
