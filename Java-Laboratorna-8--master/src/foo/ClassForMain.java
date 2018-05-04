package foo;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ClassForMain {
    public static void main(String args[]) throws SQLException {
        Product p= new Product("cucumber", LocalDate.of(2017, 2, 7), 1, 10.50);
        Product p2= new Product("milk", LocalDate.of(2017, 2, 7), 1, 11.50);
        Product p3= new Product("orange", LocalDate.of(2017, 2, 7), 1, 2.50);
        Basket b = new Basket(LocalDate.of(1964, 2, 7),"yellow",6);
        b.addNewProducts(p);
        b.addNewProducts(p2);
        b.addNewProducts(p3);
        List<Product> pq= new DataBaseMethods().getAllProducts();


       // new DataBaseMethods().deleteBasket(51);

      //  new DataBaseMethods().insertBasket(b);
      /*  try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_jaba_lab6","root","root");
        } catch (SQLException e) {
            System.out.println("asdf");
        }
        List<Basket> b = null;
        try {
            b = new DataBaseMethods().getAllBaskets();
            for(Basket temp_p : b){
                System.out.println(temp_p.getColor());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
      //new DataBaseMethods().updateBakset(34, LocalDate.of(2017,10,10),"Black",10);
       /* try {
            List<Product> p = new DataBaseMethods().getProductsByName("cucumber");
            for(Product temp_p: p){
                System.out.println(temp_p.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
}


