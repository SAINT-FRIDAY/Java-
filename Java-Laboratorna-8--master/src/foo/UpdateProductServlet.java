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

@WebServlet(name = "UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
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
            status=true;
            int id=Integer.parseInt(request.getParameter("product_id")); //Апдейтимо по цьому
            int origin_id=Integer.parseInt(request.getParameter("product_origin"));


            Pattern for_name = Pattern.compile("^[a-zA-Z]+$");
            String new_product_name =request.getParameter("product_name");
            Matcher match = for_name.matcher(new_product_name);
            if (!match.find()) {
                out.println("Incorrect name.Letters only");
                status = false;
            }
            String new_name = new_product_name; //Ім'я

            String new_basket_date_string = request.getParameter("product_date");

            String year;
            String month;
            String date;
            String delims = "-";
            String[] tokens = new_basket_date_string.split(delims);
            LocalDate new_date = LocalDate.of(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));//Нова дата

            int r = Integer.parseInt(request.getParameter("product_shelftime")); //Новий термін придатності
            if(r<=0){
                out.println("Incorrect ShelfTime. It must bigger than 0");
                status=false;
            }
            Double r2 = Double.parseDouble(request.getParameter("product_price"));
            if(r2<=0){
                    out.println("Incorrect Price.It must be bigger than 0");
                    status=false;
            }
            if(status){
                List<Product> products=new ArrayList<Product>();
                try {
                    new DataBaseMethods().updateProduct(id,new_name,new_date,r,r2);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    products =new DataBaseMethods().getProductsByOriginId(origin_id);
                } catch (SQLException e) {
                    out.println("sql exception");
                }
                request.setAttribute("products", products);
                request.setAttribute("id",origin_id);
                request.getRequestDispatcher("products_page.jsp").forward(request, response);
            }
            else{
                out.println(" Product was not updated.Please,check your mistakes and try again");
            }
        }
        if(request.getParameter("delete") != null) {
            int id=Integer.parseInt(request.getParameter("product_id"));
            int origin_id=Integer.parseInt(request.getParameter("product_origin"));
            List<Product> products=new ArrayList<Product>();
            try {
                new DataBaseMethods().deleteProductByID(id);
            } catch (SQLException e) {
                out.println("sql exception");
            }
            try {
                products =new DataBaseMethods().getProductsByOriginId(origin_id);
            } catch (SQLException e) {
                out.println("sql exception");
            }
            request.setAttribute("products", products);
            request.setAttribute("id",origin_id);
            request.getRequestDispatcher("products_page.jsp").forward(request, response);
            // String message = "Example source code of Servlet to JSP communication.";
            //request.setAttribute("message", message);
        }
        if(request.getParameter("add_product") != null) {
            int origin_id=Integer.parseInt(request.getParameter("product_origin"));
            request.setAttribute("origin_id", origin_id);

            request.getRequestDispatcher("add_product.jsp").forward(request, response);
        }
        }

//add_product

}
