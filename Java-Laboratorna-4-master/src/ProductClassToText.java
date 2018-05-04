
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;

public class ProductClassToText implements Serializaer<Product>{
	@Override
	public void serialize(Product object, File output) throws IOException {
		Writer whereToWrite = new PrintWriter(output);
		whereToWrite.write(object.toString());
		whereToWrite.flush();
		whereToWrite.close();
	}
	
	@Override 
	public void serializeCollection(List<Product> objects, File output) throws IOException {
		Writer whereToWrite = new PrintWriter(output);
		for(Product z: objects) {
			whereToWrite.write(z.toString()); 
			whereToWrite.write("\n");			
		}
		whereToWrite.flush();
		whereToWrite.close();
	}
	@Override
	public Product deserialize(File input) throws FileNotFoundException {
		Scanner scanner = new Scanner(input, "UTF-8");
        String inputText = scanner.useDelimiter("\\A").next();
        scanner.close();
      
       Product temp =new Product();
       Pattern for_name = Pattern.compile("(?<=This is )([a-zA-Z]*)(?=,which)");
   	   Matcher match = for_name.matcher(inputText);
   	   match.find(); 
   	   
   	   String name = match.group().replaceAll("\\s+","");       	       
    
   	   
    	Pattern for_year = Pattern.compile("(?<=created on ).{4}");
    	match = for_year.matcher(inputText);
    	if(!match.find()) {
    		throw new IllegalArgumentException("Incorrect name");
    	}
    	
    	int year = Integer.parseInt(match.group(0));
    	
    	
    	Pattern for_month = Pattern.compile("(?<=-)(.*)(?=-)");
    	match = for_month.matcher(inputText);
		if(!match.find()) {
			throw new IllegalArgumentException();
		}
		int month = Integer.parseInt(match.group());

		 Pattern for_day = Pattern.compile(".{2}(?=$)");
		 match = for_day.matcher(inputText);
		 if(!match.find()) {
				throw new IllegalArgumentException();
			}
		 int day= Integer.parseInt(match.group());
		 
		 LocalDate temp_year = LocalDate.of(year, month, day);
		 
		 temp.setDateOfCreation(temp_year);
		 temp.setName(name);
		 return temp;
	}
	public List<Product> deserializeCollection(File input) throws FileNotFoundException{
		Scanner scanner = new Scanner(input, "UTF-8");
        String inputText = scanner.useDelimiter("\\A").next();
        Product temp =new Product();
        List<Product> products= new ArrayList<Product>();
        for(String s: inputText.split("\n")) {
        	if(!s.isEmpty()) {
        		 Pattern for_name = Pattern.compile("(?<=This is )(.*)(?=,which)");
        	   	   Matcher match = for_name.matcher(s);
        	   	   match.find(); 
        	   	   
        	   	   String name = match.group().replaceAll("\\s+","");       	       
        	    
        	   	   
        	    	Pattern for_year = Pattern.compile("(?<=created on ).{4}");
        	    	match = for_year.matcher(s);
        	    	match.find();
        	    	int year = Integer.parseInt(match.group(0));;
        	    	
        	    	
        	    	Pattern for_month = Pattern.compile("(?<=-)(.*)(?=-)");
        	    	match = for_month.matcher(s);
        			match.find();
        			int month = Integer.parseInt(match.group());

        			 Pattern for_day = Pattern.compile(".{2}(?=$)");
        			 match = for_day.matcher(s);
        			 match.find();
        			 int day= Integer.parseInt(match.group());
        			 
        			 LocalDate temp_year = LocalDate.of(year, month, day);
        			 
        			 temp.setDateOfCreation(temp_year);
        			 products.add(new Product(name,LocalDate.of(year,month,day)));
        	}
        }
        scanner.close();
        return products; 
	}
	
	
	
	
	
	
	
	
	/*public void serialize(Product product,Writer output) throws IOException {
		output.write(product.toString());
		output.flush();
		output.close();
	}
	public void serializeCollection(List<Product> products,Writer output) throws IOException {
		for(Product z: products) {
			output.write(z.toString()); 
			output.write("\n");			
		}
		output.flush();
		output.close();
	}
	
	public Product deserialize (InputStream input) {
		Scanner scanner = new Scanner(input, "UTF-8");
        String inputText = scanner.useDelimiter("\\A").next();
        scanner.close();
      
       Product temp =new Product();
       Pattern for_name = Pattern.compile("(?<=This is )([a-zA-Z]*)(?=,which)");
   	   Matcher match = for_name.matcher(inputText);
   	   match.find(); 
   	   
   	   String name = match.group().replaceAll("\\s+","");       	       
    
   	   
    	Pattern for_year = Pattern.compile("(?<=created on ).{4}");
    	match = for_year.matcher(inputText);
    	if(!match.find()) {
    		throw new IllegalArgumentException();
    	}
    	
    	int year = Integer.parseInt(match.group(0));
    	
    	
    	Pattern for_month = Pattern.compile("(?<=-)(.*)(?=-)");
    	match = for_month.matcher(inputText);
		if(!match.find()) {
			throw new IllegalArgumentException();
		}
		int month = Integer.parseInt(match.group());

		 Pattern for_day = Pattern.compile(".{2}(?=$)");
		 match = for_day.matcher(inputText);
		 if(!match.find()) {
				throw new IllegalArgumentException();
			}
		 int day= Integer.parseInt(match.group());
		 
		 LocalDate temp_year = LocalDate.of(year, month, day);
		 
		 temp.setDateOfCreation(temp_year);
		 temp.setName(name);
		 return temp;
	}
	public List<Product> deseriazlizeCollection(InputStream input){
		Scanner scanner = new Scanner(input, "UTF-8");
        String inputText = scanner.useDelimiter("\\A").next();
        Product temp =new Product();
        List<Product> products= new ArrayList<Product>();
        for(String s: inputText.split("\n")) {
        	if(!s.isEmpty()) {
        		 Pattern for_name = Pattern.compile("(?<=This is )(.*)(?=,which)");
        	   	   Matcher match = for_name.matcher(s);
        	   	   match.find(); 
        	   	   
        	   	   String name = match.group().replaceAll("\\s+","");       	       
        	    
        	   	   
        	    	Pattern for_year = Pattern.compile("(?<=created on ).{4}");
        	    	match = for_year.matcher(s);
        	    	match.find();
        	    	int year = Integer.parseInt(match.group(0));;
        	    	
        	    	
        	    	Pattern for_month = Pattern.compile("(?<=-)(.*)(?=-)");
        	    	match = for_month.matcher(s);
        			match.find();
        			int month = Integer.parseInt(match.group());

        			 Pattern for_day = Pattern.compile(".{2}(?=$)");
        			 match = for_day.matcher(s);
        			 match.find();
        			 int day= Integer.parseInt(match.group());
        			 
        			 LocalDate temp_year = LocalDate.of(year, month, day);
        			 
        			 temp.setDateOfCreation(temp_year);
        			 products.add(new Product(name,LocalDate.of(year,month,day)));
        	}
        }
        scanner.close();
        return products; 
	}*/
	
	/*
	 * 
	 * public List<Product> deseriazlizeCollection(InputStream input){
		Scanner scanner = new Scanner(input, "UTF-8");
        String inputText = scanner.useDelimiter("\\A").next();
        Product temp =new Product();
        List<Product> products= new ArrayList<Product>();
        for(String s: inputText.split("\n")) {
        	if(!s.isEmpty()) {
        		 Pattern for_name = Pattern.compile("(?<=This is )(.*)(?=,which)");
        	   	   Matcher match = for_name.matcher(s);
        	   	   match.find(); 
        	   	   
        	   	   String name = match.group().replaceAll("\\s+","");
        	       
        	    
        	    	Pattern for_year = Pattern.compile("(?<=created on ).{4}");
        	    	Matcher match2 = for_year.matcher(s);
        	    	match2.find();
        	    	int year = Integer.parseInt(match2.group(0));;
        	    	
        	    	
        	    	Pattern for_month = Pattern.compile("(?<=-)(.*)(?=-)");
        			Matcher match3 = for_month.matcher(s);
        			match3.find();
        			int month = Integer.parseInt(match3.group());

        			 Pattern for_day = Pattern.compile(".{2}(?=$)");
        			 Matcher match4 = for_day.matcher(s);
        			 match4.find();
        			 int day= Integer.parseInt(match4.group());
        			 
        			 LocalDate temp_year = LocalDate.of(year, month, day);
        			 
        			 temp.setDateOfCreation(temp_year);
        			 products.add(new Product(name,LocalDate.of(year,month,day)));
        	}
        }
        return products; 
	}
	 * 
	 * 
	 * 
	 * 
	 */
	/*public void writeObjectToFile(Product object) throws IOException {
		FileOutputStream fos = new FileOutputStream("temp.txt");
		  ObjectOutputStream oos = new ObjectOutputStream(fos);
		  List<Product> products =new ArrayList<Product>();
			products.add(object);
		  oos.writeObject(object);	   
		  oos.close();
	}
	
	public void listOfProductsToTextFIle(List<Product> products) throws IOException {
		FileOutputStream fos = new FileOutputStream("temp.txt");
		  ObjectOutputStream oos = new ObjectOutputStream(fos);
		  oos.writeObject(products);
		  oos.close();
	}
	public List<Product> getProductFromFile() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("temp.txt");
		  ObjectInputStream oin = new ObjectInputStream(fis);
		  List<Product> products =new ArrayList<Product>();
		  Object p;
		 
		 
		  p= oin.readObject();
		  products = (List<Product>) p;
		  return products;
	}
	public void makeFileEmpty() throws IOException {
		PrintWriter writer = new PrintWriter("temp.txt");
		writer.print("");
		writer.close();
		  List<Product> products =new ArrayList<Product>();
		 
		  oos.writeObject(products);
		  oos.close();  
	}*/
	
	
	
	
	
}
