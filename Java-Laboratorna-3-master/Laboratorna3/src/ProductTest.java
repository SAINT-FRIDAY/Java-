
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import java.util.Set;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class ProductTest {

	@BeforeMethod
	public void start() {

	}

	/////////////////////////////////////////////// IsGoog/////////////////////////////////////////
	@DataProvider
	public Object[][] dataProviderForIsGoodTest() {
		Product main1, main2;
		main1 = new Product("milk", LocalDate.of(2017, Month.JUNE, 9), 9); // Не просрочений
		main2 = new Product("tea", LocalDate.of(2017, Month.JUNE, 1), 2); // Просрочений
		return new Object[][] { { main1, true }, { main2, false } };
	}

	@Test(dataProvider = "dataProviderForIsGoodTest")
	public void isGoodTest(Product obj, boolean res) {
		Assert.assertEquals(obj.isGood(), res);
	}

	/////////////////////////////////////// HowManyDaysLeft////////////////////////////////////////////////////////
	@DataProvider
	public Object[][] dataProviderForHowManyDaysLeft() {
		Product test_object3, test_object4;
		test_object3 = new Product("milk", LocalDate.of(2017, Month.JUNE, 9), 9); // Не просрочений
		test_object4 = new Product("tea", LocalDate.of(2017, Month.JUNE, 1), 2); // Просрочений
		return new Object[][] { { test_object3, 167 }, { test_object4, 0 } };
	}

	@Test(dataProvider = "dataProviderForHowManyDaysLeft")
	public void howManyDaysLeftTest(Product object, int res) {
		Assert.assertEquals(object.howManyDaysLeft(), res);
	}

	////////////////////////////////////// ToString/////////////////////////////////////////////////////
	@DataProvider
	public Object[][] dataProviderForToStringTest() {
		Product test_object3, test_object4;
		test_object3 = new Product("milk", LocalDate.of(2017, Month.JUNE, 9), 9); // Не просрочений
		test_object4 = new Product("tea", LocalDate.of(2017, Month.JUNE, 1), 2); // Просрочений
		return new Object[][] { { test_object3, "This is milk ,which was created on 2017-06-09" },
				{ test_object4, "This is tea ,which was created on 2017-06-01" } };
	}

	@Test(dataProvider = "dataProviderForToStringTest")
	public void toStringTest(Product obj, String res) {
		Assert.assertEquals(obj.toString(), res);
	}

	/////////////////////////////////// HashCode/////////////////////////////////////////////////////
	@DataProvider
	public Object[][] dataProviderForHashCode() {
		Product test_object3, test_object4;
		test_object3 = new Product("potato", LocalDate.of(2017, Month.JUNE, 9), 9);
		test_object4 = new Product("potato", LocalDate.of(0, 1, 1), 0);
		return new Object[][] { { test_object3, test_object4 } };
	}

	@Test(dataProvider = "dataProviderForHashCode")
	public void hashTest(Product obj1, Product obj2) {
		Assert.assertEquals(obj1.hashCode(), obj2.hashCode());
	}

}
