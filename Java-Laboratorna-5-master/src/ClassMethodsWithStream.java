

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlRootElement;
public class ClassMethodsWithStream {
	private Basket b;
	private  final static double EPS =0.0001;
	ClassMethodsWithStream(Basket b){
		this.b=b;
	}
	public double getTheHighestPrice() {
		return this.b.getListOfProduct().stream().mapToDouble(Product::getPrice).max().orElse(-1);
	}
	public double getTheSmallestPrice() {
		return this.b.getListOfProduct().stream().mapToDouble(Product::getPrice).min().orElse(-1);
	}
	public List<Product> getCheapestProducts() {
		double min_price= new ClassMethodsWithStream(this.b).getTheSmallestPrice();
		List<Product> products=  this.b.getListOfProduct().stream().filter(x-> x.getPrice()-min_price<this.EPS).collect(Collectors.toList());
		return products;
	}
	public List<Product> getTheMostExpensiveProducts(){
		double max_price = new ClassMethodsWithStream(this.b).getTheHighestPrice();
		List<Product> products=  b.getListOfProduct().stream().filter(x-> max_price-x.getPrice()<this.EPS).collect(Collectors.toList());
		return products;
	}
	public List<Product> sortProductsFormTheLowestPriceToTheHighest(){
		return this.b.getListOfProduct().stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
	}
	public List<Product> sortProductsFromTheHighestPriceToTheLowest(){
		return this.b.getListOfProduct().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
	}
	public int countSomeProduct(Product p) {
		 return (int)this.b.getListOfProduct().stream().filter(x->x.getName().equals(p.getName())).count();
	}
	public int getAmountOfProducts() {
		 return (int)this.b.getListOfProduct().stream().count();
	}
	public void deleteSomeProducts(Product p) {
		this.b.setSetOfProducts(this.b.getListOfProduct().stream().filter(x->!x.getName().equals(p.getName())).collect(Collectors.toList()));
	}
	
	
	
	
}
