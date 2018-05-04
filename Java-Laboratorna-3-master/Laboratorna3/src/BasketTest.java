
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;


public class BasketTest {

	Basket b1,b2,b3;
	Product test_product1,test_product2,test_product3;
	@BeforeTest
	public void start() {
		b1=new Basket(LocalDate.of(2017, 1,2),"green",1);
		b2=new Basket(LocalDate.of(2017, 3,4),"blue",2);
		b3 =new Basket(LocalDate.of(2017, 5,6),"white",3);
		test_product1= new Product("potato",LocalDate.of(2017, 1, 1),1,10.50);
		test_product2= new Product("milk",LocalDate.of(2017, 3, 3),3,5.0);
		test_product3=new Product("cucmber",LocalDate.of(2017, 4, 4),4,6.75);
		b1.addNewProducts(test_product2);
		b1.addNewProducts(test_product3);
		b1.addNewProducts(test_product1);
		b2.addNewProducts(test_product1);
		b2.addNewProducts(test_product3);
		b2.addNewProducts(test_product2);
		b3.addNewProducts(test_product1);
		b3.addNewProducts(test_product2);
		b3.addNewProducts(test_product3);
	}
	
	/*@DataProvider
	public Object[][] toStringProvider(){
		return new Object[][] {{b1,"This is green basket of size: 1"},{b2,"This is blue basket of size: 2"}};
	}
	@Test(dataProvider="toStringProvider")
	public void toStringTest(Basket obj,String res) {
		Assert.assertEquals(obj.toString(),res);
	}*/
	
	@DataProvider 
	public Object[][] getAmountOfProductProvider(){
		return new Object[][] {{b1,3},{b2,3},{b3,3}};
	} 
	@Test(dataProvider="getAmountOfProductProvider")
	public void getAmountOfProductsTest(Basket obj,int res) {
		Assert.assertEquals(obj.getAmountOfPrdoucts(), res);
	}
	
	@DataProvider
	public Object[][] findSomeProductsProvider(){
		return new Object[][] {{b1,"milk",1},{b2,"cucumber",0}};
	}
	@Test(dataProvider="findSomeProductsProvider")
	public void findSomeProductsTest(Basket obj,String whatToFind,int res){
		Assert.assertEquals(obj.countSomeProduct(whatToFind), res);
	}
	
	/*
	 * remove products
	 */
	@DataProvider
	public Object[][] removeProductProvider(){
		return new Object[][] {{b1,"milk",true},{b2,"cucumber",false}};
	}
	@Test(dataProvider="removeProductProvider",enabled=false)
	public void removeProductTest(Basket basket, String whatToRemove,boolean res) {
		Assert.assertEquals(basket.removeProduct(whatToRemove), res);
	}
	@DataProvider
	public Object[][] findTheCheapestProductProvider(){
		List<Product> temp= new ArrayList<Product>();
		temp.add(test_product2);
		return new Object[][] {{b3,temp}};
	}
	@Test(dataProvider="findTheCheapestProductProvider")
	public void findTheCheapestProductTest(Basket obj,List<Product> res) {
		Assert.assertTrue(obj.getTheCheapsetProduct().containsAll(res)&&res.containsAll(obj.getTheCheapsetProduct()));
	}
	@DataProvider 
	public Object[][] findTheMostExpensiveProductProvider(){
		List<Product> temp= new ArrayList<Product>();
		temp.add(test_product1);
		return new Object[][] {{b3,temp}};
	}
	@Test(dataProvider="findTheMostExpensiveProductProvider")
	public void findTheMostExpensiveProductTest(Basket obj,List<Product> res) {
		Assert.assertTrue(res.containsAll(obj.getTheMostExpensiveProduct())&&obj.getTheMostExpensiveProduct().containsAll(res));
	}
	@DataProvider
	public Object[][] sortProductsByPriceNormalProvider(){
		List<Product> temp= new ArrayList<Product>();
		temp.add(test_product2);
		temp.add(test_product3);
		temp.add(test_product1);
		return new Object[][] {{b3,temp}};
	}
	@Test(dataProvider="sortProductsByPriceNormalProvider")
	public void sortProductsByPriceNormanlTest(Basket obj, List<Product> res) {
		obj.sortProductsByPriceNormal();
		Assert.assertEquals(obj.getListOfProduct().get(0),res.get(0));
		Assert.assertEquals(obj.getListOfProduct().get(1),res.get(1));
		Assert.assertEquals(obj.getListOfProduct().get(2),res.get(2));	
	}
	@DataProvider
	public Object[][] sortProductsByPriceReverseProvider(){
		List<Product> temp= new ArrayList<Product>();
		temp.add(test_product1);
		temp.add(test_product3);
		temp.add(test_product2);
		return new Object[][] {{b3,temp}};
	}
	@Test(dataProvider="sortProductsByPriceReverseProvider")
	public void sortProductsByPriceReverseTest(Basket obj,List<Product> res) {
		obj.sortProductsByPriceReverse();
		Assert.assertEquals(obj.getListOfProduct().get(0),res.get(0));
		Assert.assertEquals(obj.getListOfProduct().get(1),res.get(1));
		Assert.assertEquals(obj.getListOfProduct().get(2),res.get(2));	
	}
}



