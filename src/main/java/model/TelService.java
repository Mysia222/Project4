package model;

import main.ent.Service;
import main.ent.Subscriber;
import main.jdbc.JDBCRunner;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Славик on 17.07.2016.
 */
public class TelService {
    private static TelService instance = new TelService();
    public static TelService getInstance(){return instance;}


    private InitialContext ic;
    private DataSource ds;
    private TelService(){}






    private Connection getConnection() throws SQLException, NamingException {
        ic = new InitialContext();
        ds = (DataSource) ic.lookup("java:comp/env/jdbc/mysql"); // вместо написать java:/comp/env/jdbc/TestDB
        return ds.getConnection();
    }

    public double getBalance(HttpServletRequest request) throws NamingException, SQLException {
        PreparedStatement ps = this.getConnection().prepareStatement( "SELECT * FROM daotalk.abonents WHERE login = ?");
        ps.setString(1,request.getParameter("login"));
        ResultSet rs = ps.executeQuery();
        while( rs.next()){
            return rs.getDouble("balance");
        }
        ps.close();
        return 0;
    }

    public ConcurrentMap<Integer,Service> getServiceList() throws SQLException, NamingException {
        Statement statement = new JDBCRunner().getConnection().createStatement();
        String s = "SELECT * FROM  daotalk.tel_service;";
        ResultSet rs = statement.executeQuery(s);
        ConcurrentMap<Integer,Service> map = new ConcurrentHashMap<Integer, Service>();
        while (rs.next()){
            map.putIfAbsent(rs.getInt("id"),new Service(rs.getString("name"),rs.getDouble("price")));
        }
        return map;
    }

    public void changeBalance(Subscriber subscriber) throws SQLException, NamingException {
        Statement statement = new JDBCRunner().getConnection().createStatement();
        String s = "UPDATE daotalk.abonents SET balance='"+subscriber.getBalance()+"' WHERE contract='"+subscriber.getContract()+"';";
        statement.execute(s);
    }
    public HttpServletRequest reRequest(HttpServletRequest request) throws SQLException, NamingException {
        Statement statement = new JDBCRunner().getConnection().createStatement();
        String s = "SELECT * FROM daotalk.abonents WHERE login='"+request.getParameter("login")+"';";
        ResultSet rs = statement.executeQuery(s);
        if (rs.next()){
            if (!request.getParameter("password").equals(rs.getString("password"))){
                request.setAttribute("wrongPassword", "Wrong password!");
            }
        }else {
            request.setAttribute("wrongLogin", "Wrong login!");
        }
        return request;
    }
    public boolean isExist(String login, String password){
        try {
            Statement statement = new JDBCRunner().getConnection().createStatement();
            String s = "SELECT * FROM daotalk.abonents WHERE login='"+login+"' and password='"+password+"';";

            ResultSet rs = statement.executeQuery(s);
            return rs.next();
//            while (rs.next()){
//
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Subscriber getSub(HttpServletRequest request) throws SQLException, NamingException {
        Subscriber sub = new Subscriber();
        String log = request.getParameter("login");
        String pass = request.getParameter("password");
        String contract = request.getParameter("contract");
    if (log!=null&&pass!=null) {
        Statement statement = new JDBCRunner().getConnection().createStatement();
        String s = "SELECT * FROM daotalk.abonents WHERE login='" + log
                + "' and password='" + pass + "';";
        ResultSet rs = statement.executeQuery(s);
        while (rs.next()) {
            sub.setBalance(rs.getDouble("balance"));
            sub.setContract(rs.getInt("contract"));
            sub.setInfo(sub.new SubInfo(rs.getString("second_name"), rs.getString("first_name"), pass, log));
        }
        return sub;
    }else if (contract!=null){
        Statement statement = new JDBCRunner().getConnection().createStatement();
        String s = "SELECT * FROM daotalk.abonents WHERE contract='" + contract + "';";
        ResultSet rs = statement.executeQuery(s);
        while (rs.next()) {
            sub.setBalance(rs.getDouble("balance"));
            sub.setInfo(sub.new SubInfo(rs.getString("second_name"), rs.getString("first_name"),
                    rs.getString("login"), rs.getString("password")));
        }
        return sub;
    }
        return sub;
    }
    public void setSub(Subscriber subscriber) throws SQLException, NamingException {
        Statement statement = new JDBCRunner().getConnection().createStatement();
        String s = "UPDATE daotalk.abonents SET login='"+subscriber.getInfo().getLogin()+
                "' ,password='"+subscriber.getInfo().getPassword()+"' ,first_name='"+subscriber.getInfo().getFirstName()+
                "', second_name='"+subscriber.getInfo().getSecondName()+"' WHERE contract='"+subscriber.getContract()+"';";
        statement.execute(s);
    }
}
