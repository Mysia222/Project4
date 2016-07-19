package main.jdbc;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Славик on 16.07.2016.
 */
    public class DAO {
    private InitialContext ic;
    private DataSource ds;

    public Connection getConnection() throws SQLException, NamingException {
        ic = new InitialContext();
        ds = (DataSource) ic.lookup("java:comp/env/jdbc/mysql");
        return ds.getConnection();
    }


    }

