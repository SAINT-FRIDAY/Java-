import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import java.util.List;

public class TestForFiles {
	Product test_product1,test_product2,test_product3;
	List<Product> products;
  @BeforeTest
  public void start() {
	  
	   test_product1= new Product("milk",LocalDate.of(2017,1,7));
	   test_product2= new Product("cucmber",LocalDate.of(2016, 7, 8));
	   test_product3= new Product("potato",LocalDate.of(2017,9,8));
	  products= new ArrayList<Product>();
	  products.add(test_product1);
	  products.add(test_product2);
	  products.add(test_product3);
  }
  @DataProvider
  public Object[][] dataProviderForSerializationOfOneObject(){
	  return new Object[][] {{test_product1},{test_product2},{test_product3}};
  }
  @DataProvider
  public Object[][] dataProviderForSerializationOfList(){
	  return new Object[][] {{products}};
  }
  @Test(dataProvider= "dataProviderForSerializationOfOneObject")
  public void serializationToText(Product p) throws IOException {
	  new ProductClassToText().serialize(p,new File("in.txt"));
	  Assert.assertEquals(new ProductClassToText().deserialize(new File("in.txt")), p);
  }
  @Test(dataProvider ="dataProviderForSerializationOfList")
  public void test(List<Product> products) throws FileNotFoundException, IOException {
	  new ProductClassToText().serializeCollection(products, new File("in.txt"));
	  Assert.assertEquals(new ProductClassToText().deserializeCollection(new File("in.txt")), products);
  }
  @Test(dataProvider= "dataProviderForSerializationOfOneObject")
  public void serializationToXML(Product p) throws FileNotFoundException {
	  new ProductClassToXML().serialize(p, new File("in2.xml"));
	  Assert.assertEquals(new ProductClassToXML().deserialize(new File("in2.xml")), p);
  }
  @Test(dataProvider = "dataProviderForSerializationOfList")
  public void serializationOfListToXML(List<Product> p) throws FileNotFoundException {
	  new ProductClassToXML().serializeCollection(p, new File("in2.xml"));
	  Assert.assertEquals(new ProductClassToXML().deserializeCollection(new File("in2.xml")), p); 
  }
  @Test(dataProvider = "dataProviderForSerializationOfOneObject")
  public void serializationtoJSON(Product p) throws JsonGenerationException, JsonMappingException, IOException {
	  new ClassProductToJSON().serialize(p, new File("in3.json"));
	  Assert.assertEquals(new ClassProductToJSON().deserialize(new File("in3.json")), p);
  }
  
  
  
  
  

}
