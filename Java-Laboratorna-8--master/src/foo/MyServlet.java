package foo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static sun.plugin.javascript.navig.JSType.Location;

@WebServlet(name = "MyServlet")
public class MyServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            out.print("ClassNotFOund");
        }


        String operation = request.getParameter("operation");

        String id = request.getParameter("prodId");

        //List<Basket> b=null;
       // List<Product> p=null;
        if(request.getParameter("view") != null) {

            if (operation.equals("All Baskets"))
            {
                List<Basket> baskets= new ArrayList<Basket>();
                try {
                    baskets = new DataBaseMethods().getAllBaskets();
                } catch (SQLException e) {
                    out.print("Sql exception");
                }
               // String message = "Example source code of Servlet to JSP communication.";
                //request.setAttribute("message", message);
                request.setAttribute("baskets", baskets);
                request.getRequestDispatcher("information_page.jsp").forward(request, response);

            }

        }

      //  out.print("CLOSE ,");
        out.close();
    }
}
