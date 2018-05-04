package foo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "AddBasketServlet")
public class AddBasketServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println("statred");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            out.print("ClassNotFOund");
        }

            boolean status=true;
            String new_basket_date_string=request.getParameter("new_basket_date");
            String year;
            String month;
            String date;
            String delims = "-";
            String[] tokens = new_basket_date_string.split(delims);
            LocalDate new_date= LocalDate.of(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2])); //Дата


            Pattern for_color = Pattern.compile("^[a-zA-Z]+$");
            String new_basket_color_string=request.getParameter("new_basket_color");
            Matcher match = for_color.matcher(new_basket_color_string);

            if(!match.find()){
                out.println("Incorrect color.Letters only");
                status=false;
            }
            String new_color=new_basket_color_string; // Колір

            int r= Integer.parseInt(request.getParameter("new_basket_spaciousness"));
            if(r<=0){
                out.println("Incorrect spaciousness.Value musy be bigger than 0");
                status=false;
            }
            out.println("Yes");
            if(status){
                Basket new_basket = new Basket(new_date,new_color,r);
                try {
                    new DataBaseMethods().insertBasket(new_basket);
                } catch (SQLException e) {
                    out.println("SqlException");
                }
                List<Basket> baskets= new ArrayList<Basket>();
                try {
                    baskets = new DataBaseMethods().getAllBaskets();
                } catch (SQLException e) {
                    out.print("Sql exception");
                }

                request.setAttribute("baskets", baskets);
                request.getRequestDispatcher("information_page.jsp").forward(request, response);
            }
            else{
                out.println("New Basket was not added.Please,check your mistakes and try again");
            }
            out.println("end of work");

    }
}
