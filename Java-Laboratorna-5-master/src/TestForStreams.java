import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

public class TestForStreams {
	Basket b;
	Product test_product1, test_product2, test_product3, test_product4;
	ClassMethodsWithStream test_object;
	@BeforeTest
	void setup() {
		test_product1 = new Product("milk", LocalDate.of(2017, 2, 7), 1, 2.50);
		test_product2 = new Product("cucumber", LocalDate.of(2017, 1, 1), 1, 10.50);
		test_product3 = new Product("orange", LocalDate.of(2017, 1, 1), 1, 7.50);
		test_product4 = new Product("chocolate", LocalDate.of(2017, 2, 7), 1, 20.50);
		List<Product> products = new ArrayList<Product>();
		products.add(test_product1);
		products.add(test_product2);
		products.add(test_product3);
		products.add(test_product4);
		// products.add(test_product1);
		b = new Basket(LocalDate.of(2017, 2, 7), "yellow", 3, products);
		test_object = new ClassMethodsWithStream(b);
	}

	@DataProvider
	public Object[][] dataProviderForTheSmallestPrice() {
		return new Object[][] { { b, 2.50 } };
	}

	@Test(dataProvider = "dataProviderForTheSmallestPrice")
	public void testTheSmallestPriceMethod(Basket basket, double price) {
		Assert.assertEquals(new ClassMethodsWithStream(basket).getTheSmallestPrice(), 2.5);
	}

	@DataProvider
	public Object[][] dataProviderForTheHighestPrice() {
		return new Object[][] { { b, 20.50 } };
	}

	@Test(dataProvider = "dataProviderForTheHighestPrice")
	public void testTheHighestPriceMethod(Basket basket, double price) {
		Assert.assertEquals(new ClassMethodsWithStream(basket).getTheHighestPrice(), price);
	}

	@DataProvider
	public Object[][] testForDirectSortProvider() {
		List<Product> products2 = new ArrayList<Product>();
		products2.add(test_product1);
		products2.add(test_product3);
		products2.add(test_product2);
		products2.add(test_product4);
		return new Object[][] { { b, products2 } };
	}

	@Test(dataProvider = "testForDirectSortProvider")
	public void testForDirectSort(Basket basket, List<Product> p) {
		Assert.assertEquals(new ClassMethodsWithStream(basket).sortProductsFormTheLowestPriceToTheHighest(), p);
	}

	@DataProvider
	public Object[][] testForReverseSortProvider() {
		List<Product> products2 = new ArrayList<Product>();
		products2.add(test_product4);
		products2.add(test_product2);
		products2.add(test_product3);
		products2.add(test_product1);
		return new Object[][] { { b, products2 } };
	}
	
	@Test(dataProvider = "testForReverseSortProvider")
	public void testForReverseSort(Basket basket, List<Product> p) {
		Assert.assertEquals(new ClassMethodsWithStream(basket).sortProductsFromTheHighestPriceToTheLowest(), p);
	}
	
	@DataProvider
	public Object[][] testForGetProductsWithTheHighestPriceProvider(){
		Basket b2=new Basket(LocalDate.of(2017, 2, 7), "yellow", 3);;
		b2.addNewProducts(test_product1);
		b2.addNewProducts(test_product2);
		b2.addNewProducts(test_product3);
		b2.addNewProducts(test_product4);
		b2.addNewProducts(test_product4);
		List<Product> products2 = new ArrayList<Product>();
		products2.add(test_product4);
		products2.add(test_product4);
		return new Object[][] {{b2,products2}};
	}
	@Test(dataProvider="testForGetProductsWithTheHighestPriceProvider")
	public void testForGetProductsWithTheHighestPrice(Basket basket, List<Product> p) {
		Assert.assertEquals(new ClassMethodsWithStream(basket).getTheMostExpensiveProducts(), p);
	}

	@DataProvider
	public Object[][] testForGetProductsWithTheSmallestPriceProvider(){
		Basket b2=new Basket(LocalDate.of(2017, 2, 7), "yellow", 3);;
		b2.addNewProducts(test_product1);
		b2.addNewProducts(test_product1);
		b2.addNewProducts(test_product2);
		b2.addNewProducts(test_product3);
		b2.addNewProducts(test_product4);
		b2.addNewProducts(test_product4);
		List<Product> products2 = new ArrayList<Product>();
		products2.add(test_product1);
		products2.add(test_product1);
		return new Object[][] {{b2,products2}};
	}
	@Test(dataProvider="testForGetProductsWithTheSmallestPriceProvider")
	public void testForGetProductsWithTheSmallestPrice(Basket basket, List<Product> p){
		Assert.assertEquals(new ClassMethodsWithStream(basket).getCheapestProducts(), p);
	}
	@Test
	public void getAmountOfProducts() {
		Assert.assertEquals(test_object.getAmountOfProducts(), 4);
	}
	
}
