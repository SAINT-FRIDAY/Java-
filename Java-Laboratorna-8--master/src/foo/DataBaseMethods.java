package foo;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import com.mysql.fabric.jdbc.FabricMySQLDriver;



public class DataBaseMethods {



    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_java_lab6","root","root");
        return connection;
    }
    public static void deleteProductByID(int id) throws SQLException {
        Connection conn=getConnection();
        Statement statement =conn.createStatement();
        statement.execute("delete from database_java_lab6.products where products.id="+id+";");
        conn.close();
    }
    public static void updateProduct(int new_id,String new_name,LocalDate new_date_of_creation,int new_shelf_time,double new_price) throws SQLException {
        Connection conn= getConnection();
        PreparedStatement statement= conn.prepareStatement("UPDATE  products SET name='"+new_name+"',dateofcreation='"+new_date_of_creation+"',shelftime='"+new_shelf_time+"',price='"+new_price+"' WHERE id='"+new_id+"'");
        statement.execute();
        conn.close();
    }
    public static void updateBakset(int new_id, LocalDate new_date,String new_color, int new_spaciousness) throws SQLException {
        Connection conn= getConnection();
        PreparedStatement statement= conn.prepareStatement("UPDATE baskets SET color='"+new_color+"',time_of_pick_up='"+new_date+"',spaciousness='"+new_spaciousness+"' WHERE id='"+new_id+"' ");
        statement.execute();
        conn.close();
    }
    public static void  insertProduct (Product p ) throws SQLException {
        Connection conn= getConnection();
        PreparedStatement statement = conn.prepareStatement("insert into products (name,dateofcreation,shelftime,price) values('"+p.getName()+"','"+p.getDateOfCreation()+"','"+p.getShelfTimes()+"','"+p.getPrice()+"')");
        statement.execute();
        conn.close();
    }
    public static void insertBasket(Basket b) throws SQLException {
        Connection conn= getConnection();
        PreparedStatement statement = conn.prepareStatement("insert into baskets (time_of_pick_up,color,spaciousness) values('"+b.getTimeOfPicingkUp()+"','"+b.getColor()+"','"+b.getSpaciousness()+"')",Statement.RETURN_GENERATED_KEYS);
        statement.execute();
        ResultSet rs = statement.getGeneratedKeys();
        //  ResultSet rs = statement.executeQuery("SELECT id FROM baskets WHERE time_of_pick_up='" + b.getTimeOfPicingkUp() + "',color='" + b.getColor() + "';");
        rs.next();


        Statement st =conn.createStatement();
        conn.setAutoCommit(false);
        for(Product p: b.getListOfProduct()){
            st.addBatch("insert into products (name,dateofcreation,shelftime,price,frombasket) values('" + p.getName() + "','" + p.getDateOfCreation() + "','" + p.getShelfTimes() + "','" + p.getPrice() + "','" + rs.getInt(1) + "')");
        }
        int [] count=st.executeBatch();
        conn.commit();
    }


    public   List<Product> getAllProducts() throws SQLException {
        List<Product> res = new ArrayList<Product>();
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM products;");
        while(rs.next()){

            Product p = new Product(rs.getString("name"),rs.getTimestamp("dateofcreation").toLocalDateTime().toLocalDate(),rs.getInt("shelftime"),rs.getDouble("price"),rs.getInt("id"),rs.getInt("frombasket"));
            p.setOriginId(rs.getInt("frombasket"));
           // System.out.println(p.getOrigin_id());
            res.add(p);
        }
        return res;
    }
    public static List<Product> getProductsByOriginId(int id_origin) throws SQLException {
        List<Product> res = new ArrayList<Product>();
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM products WHERE frombasket=" + id_origin + ";");
        while(rs.next()){
            Product p = new Product(rs.getString("name"),rs.getTimestamp("dateofcreation").toLocalDateTime().toLocalDate(),rs.getInt("shelftime"),rs.getDouble("price"),rs.getInt("id"),rs.getInt("frombasket"));
            p.setOriginId(rs.getInt("frombasket"));
            res.add(p);
        }
        rs.close();
        return res;
    }
    public static List<Product> getProductsByName(String input_name) throws SQLException {
        List<Product> res = new ArrayList<Product>();
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM products WHERE name='" + input_name + "';");
        while(rs.next()){
            Product p = new Product(rs.getString("name"),rs.getTimestamp("dateofcreation").toLocalDateTime().toLocalDate(),rs.getInt("shelftime"),rs.getDouble("price"),rs.getInt("id"));
            res.add(p);
        }
        rs.close();
        return res;
    }
    public static List<Basket> getAllBaskets() throws SQLException {
        List<Basket> res = new ArrayList<Basket>();
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM baskets;");
        while(rs.next()){
            Basket b= new Basket(rs.getTimestamp("time_of_pick_up").toLocalDateTime().toLocalDate(),rs.getString("color"),rs.getInt("spaciousness"),getProductsByOriginId(rs.getInt("id")),rs.getInt("id"));
            res.add(b);
        }
        rs.close();
        return res;
    }
    public static Basket getBasketById(int id) throws SQLException {
        List<Product> res = new ArrayList<Product>();
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM products WHERE id=" + id + ";");
        Basket b= new Basket(rs.getTimestamp("time_of_pick_up").toLocalDateTime().toLocalDate(),rs.getString("color"),rs.getInt("spaciousness"),getProductsByOriginId(rs.getInt("id")));
        return b;
    }
    public static void clearAllProducts() throws SQLException {
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        statement.execute("delete from database_java_lab6.products where id>0;");
        conn.close();
    }
    public static void clearAllBaskets() throws SQLException {
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        statement.execute("delete from database_java_lab6.baskets where id>0;");
        conn.close();
    }

    public static List<Product> getSortedProducts() throws SQLException {
        List<Product> res = new ArrayList<Product>();
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM database_java_lab6.products ORDER BY products.price DESC");
        while(rs.next()){
            Product p = new Product(rs.getString("name"),rs.getTimestamp("dateofcreation").toLocalDateTime().toLocalDate(),rs.getInt("shelftime"),rs.getDouble("price"));
            res.add(p);
        }
        return res;
    }
    public static List<Product> getSortedProductsFromBasket(Basket b) throws SQLException {
        List<Product> res = new ArrayList<Product>();
        Connection conn= getConnection();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM products WHERE color=" + b.getColor() + ";");
        int id_for_products =rs.getInt("id");
        Statement st = conn.createStatement();
        ResultSet rs2 = st.executeQuery("SELECT * FROM database_java_lab6.products WHERE frombasket="+id_for_products+" ORDER BY products.price DESC");
        while(rs.next()){
            Product p = new Product(rs.getString("name"),rs.getTimestamp("dateofcreation").toLocalDateTime().toLocalDate(),rs.getInt("shelftime"),rs.getDouble("price"));
            res.add(p);
        }
        return res;
    }
    public static void deleteBasket(int id) throws SQLException {
        Connection conn=getConnection();
        Statement statement =conn.createStatement();
        statement.execute("delete from database_java_lab6.products where products.frombasket="+id+";");
        Statement statement2=conn.createStatement();
        statement2.execute("delete from database_java_lab6.baskets WHERE  id="+id+"");
        conn.close();
    }
    public static void addNewProductToBasket(Product p, int basket_id) throws SQLException {
        Connection conn=getConnection();

        PreparedStatement statement = conn.prepareStatement("insert into products (name,dateofcreation,shelftime,price,frombasket) values('"+p.getName()+"','"+p.getDateOfCreation()+"','"+p.getShelfTimes()+"','"+p.getPrice()+"','"+basket_id+"')");
        statement.execute();
        conn.close();

    }


}