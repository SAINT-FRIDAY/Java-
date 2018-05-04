package foo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "UpdateBasketServlet")
public class UpdateBasketServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        boolean status=true;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            out.print("ClassNotFOund");
        }
        if(request.getParameter("update") != null) {
            int id = Integer.parseInt(request.getParameter("basket_id"));
            //out.println(id);
            String new_basket_date_string = request.getParameter("basket_date");

            String year;
            String month;
            String date;
            String delims = "-";
            String[] tokens = new_basket_date_string.split(delims);
            LocalDate new_date = LocalDate.of(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));

            int r = Integer.parseInt(request.getParameter("basket_spaciousness"));
            if (r <= 0) {
                out.println("Incoorenct spaciousness.Number must be bigger than 0");
                status = false;
            }
            Pattern for_color = Pattern.compile("^[a-zA-Z]+$");
            String new_basket_color_string = request.getParameter("basket_color");
            Matcher match = for_color.matcher(new_basket_color_string);

            if (!match.find()) {
                out.println("Incorrect color.Letters only");
                status = false;
            }
            String new_color = new_basket_color_string;
            if (status) {
                try {
                    new DataBaseMethods().updateBakset(id, new_date, new_color, r);
                } catch (SQLException e) {
                    out.println("sqlexecption");
                }
                List<Basket> baskets = new ArrayList<Basket>();
                try {
                    baskets = new DataBaseMethods().getAllBaskets();
                } catch (SQLException e) {
                    out.print("Sql exception");
                }
                // String message = "Example source code of Servlet to JSP communication.";
                //request.setAttribute("message", message);
                request.setAttribute("baskets", baskets);
                request.getRequestDispatcher("information_page.jsp").forward(request, response);
            } else {
                out.println("\" Basket was not updated.Please,check your mistakes and try again\"");
            }
        }



        if(request.getParameter("delete") != null) {
                int id=Integer.parseInt(request.getParameter("basket_id"));
            try {
                new DataBaseMethods().deleteBasket(id);
            } catch (SQLException e) {
                out.println("sql exception");
            }
            List<Basket> baskets = new ArrayList<Basket>();
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
        if(request.getParameter("show_products") != null) {
            int id=Integer.parseInt(request.getParameter("basket_id"));
            List<Product> products=new ArrayList<Product>();
            try {
               products =new DataBaseMethods().getProductsByOriginId(id);
            } catch (SQLException e) {
                out.println("sql exception");
            }
            request.setAttribute("products", products);
            request.setAttribute("id",id);
            request.getRequestDispatcher("products_page.jsp").forward(request, response);
        }
       /* String new_basket_date_string=request.getParameter("basket_date");
        String year;
        String month;
        String date;
        String delims = "-";
        String[] tokens = new_basket_date_string.split(delims);
        LocalDate new_date= LocalDate.of(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]));

        Pattern for_color = Pattern.compile("^[a-zA-Z]+$");
        String new_basket_color_string=request.getParameter("basket_color");
        Matcher match = for_color.matcher(new_basket_color_string);

        if(!match.find()){
            out.println("Incorrect color.Letters only");
            status=false;
        }
        String new_color=new_basket_color_string; // Колір

        int r= Integer.parseInt(request.getParameter("basket_color"));
        if(r<=0){
            out.println("Incorrect spaciousness.Value must be bigger than 0");
            status=false;
        }*/

       /* if(status){
            int id = Integer.parseInt(request.getParameter("basket_id"));
            try {
                new DataBaseMethods().updateBakset(id,new_date,new_color,r);
            } catch (SQLException e) {
                out.println("sqlexecption");
            }
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
        else{
            out.println("\" Basket was not updated.Please,check your mistakes and try again\"");
        }*/
    }
}
