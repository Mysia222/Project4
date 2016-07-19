package model;

import main.ent.Subscriber;
import main.jdbc.DAO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by Славик on 17.07.2016.
 */
public class TelService {
    private static TelService instance = new TelService();
    public static TelService getInstance(){return instance;}


    private InitialContext ic;
    private DataSource ds;
    private TelService(){}


    public Connection getConnection() throws SQLException, NamingException {
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


    /*
    * try {
            initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/mysql");
            Connection conn = ds.getConnection();
            Statement statement=conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM daotalk.abonents WHERE login='Qwerty123' and password='password';");
            PrintWriter out = response.getWriter();
            out.println(rs.next());
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    *
    *
    * */




    public HttpServletRequest reRequest(HttpServletRequest request) throws SQLException, NamingException {
        Statement statement = new DAO().getConnection().createStatement();
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
            Statement statement = new DAO().getConnection().createStatement();
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

        Statement statement = new DAO().getConnection().createStatement();
        String s = "SELECT * FROM daotalk.abonents WHERE login='"+log
                +"' and password='"+pass+"';";
        ResultSet rs = statement.executeQuery(s);
        while (rs.next()){
            sub.setBalance(rs.getDouble("balance"));
            sub.setContract(rs.getInt("contract"));
            sub.setInfo(sub.new SubInfo(rs.getString("second_name"), rs.getString("first_name"), pass, log ));
        }
        return sub;
    }


//    public Subscriber makeUser(){
//        Subscriber user = new Subscriber();
//        try {
//            Statement statement = new DAO().getConnection().createStatement();
//            String s = "SELECT * FROM daotalk.abonents WHERE login='"+login+"' and password='"+password+"';";
//            ResultSet rs = statement.executeQuery(s);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//    }


    public static void main() {

        System.out.println(TelService.getInstance().isExist("Qwerty123", "password"));
    }


//    public List<TelService> getAll(){
//        DaoFactory factory = DaoFactory.getFactory();
//        BouqetDao  bouqetDao = factory.createBouqetDao();
//        return bouqetDao.findAll();
//    }

//    public List find(final String name){
//        DaoFactory factory = DaoFactory.getFactory();
//        BouqetDao  bouqetDao = factory.createBouqetDao();
//        return   bouqetDao.findAll().stream().filter( new Predicate<Bouqet>() {
//
//            @Override
//            public boolean test(Bouqet t) {
//                // TODO Auto-generated method stub
//                return t.getName().contains(name);
//            }
//        }).collect(Collectors.toList());
//
//    }

}
