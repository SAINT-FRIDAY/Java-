import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.io.PrintWriter;

public class ClassFormain {
public static void  main(String[] args) throws IOException, ClassNotFoundException {
		
	Product test_product1= new Product("milk",LocalDate.of(2017, 2, 7),1,10.50);
	Product test_product2= new Product("cucumber",LocalDate.of(2017, 1, 1),1,10.50);
	List<Product> products =new ArrayList<Product>();
	List<Product> products2 =new ArrayList<Product>();
	products.add(test_product1);
	products.add(test_product2);
	
	
	
	
	
	

	 
	}
}
