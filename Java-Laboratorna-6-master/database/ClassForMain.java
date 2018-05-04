package com.devcolibri.database;

import javax.xml.crypto.Data;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClassForMain {
    public static void main(String[] args) throws SQLException {
        Product p= new Product("cucumber", LocalDate.of(2017, 2, 7), 1, 10.50);
        Product p2= new Product("milk", LocalDate.of(2017, 2, 7), 1, 11.50);
        Product p3= new Product("orange", LocalDate.of(2017, 2, 7), 1, 2.50);
        Basket b = new Basket(LocalDate.of(2012, 2, 7),"yellow",6);
        b.addNewProducts(p);
        b.addNewProducts(p2);
        b.addNewProducts(p3);

       // new DataBaseMethods().clearAllBaskets();
        //new DataBaseMethods().insertBasket(b);
    //new DataBaseMethods().clearAllProducts();
    //    new DataBaseMethods().clearAllBaskets();
        //new DataBaseMethods().clearAllProducts();

    /*List<Product> pq =new DataBaseMethods().getSortedProducts();
    for(Product uy : pq) {
        System.out.println(uy.getPrice());

    }*/


    }
}
