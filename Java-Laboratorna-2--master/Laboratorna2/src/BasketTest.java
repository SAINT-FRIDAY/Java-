
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

import java.time.LocalDate;


import org.testng.Assert;


public class BasketTest {

	Basket b1,b2,b3;
	@BeforeTest
	public void start() {
		b1=new Basket(LocalDate.of(2017, 1,2),"green",1);
		b2=new Basket(LocalDate.of(2017, 3,4),"blue",2);
		b3 =new Basket(LocalDate.of(2017, 5,6),"white",3);
		Product test_product1= new Product("potato",LocalDate.of(2017, 1, 1),1);
		Product test_product2= new Product("milk",LocalDate.of(2017, 3, 3),3);
		Product test_product3=new Product("cucmber",LocalDate.of(2017, 4, 4),4);
		b1.addNewProducts(test_product1);
		b1.addNewProducts(test_product2);
		b1.addNewProducts(test_product3);
		b2.addNewProducts(test_product1);
		b2.addNewProducts(test_product3);
		b3.addNewProducts(test_product1);
	}
	
	@DataProvider
	public Object[][] toStringProvider(){
		return new Object[][] {{b1,"This is green basket of size: 1"},{b2,"This is blue basket of size: 2"}};
	}
	@Test(dataProvider="toStringProvider")
	public void toStringTest(Basket obj,String res) {
		Assert.assertEquals(obj.toString(),res);
	}
	
	@DataProvider 
	public Object[][] getAmountOfProductProvider(){
		return new Object[][] {{b1,3},{b2,2},{b3,1}};
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
	@Test(dataProvider="removeProductProvider")
	public void removeProductTest(Basket basket, String whatToRemove,boolean res) {
		Assert.assertEquals(basket.removeProduct(whatToRemove), res);
	}

}



