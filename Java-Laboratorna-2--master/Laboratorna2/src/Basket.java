
import java.util.Set;
import java.time.LocalDate;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.List;
public class Basket  {
	private String color;
	private LocalDate timeOfPicingkUp;
	private int spaciousness; // Large // small//average 
	//private Set<Product> product;
	private List<Product> product;
	public Basket(LocalDate timeOfPickUp,String color,int spaciousness) {
		this.timeOfPicingkUp=timeOfPickUp;
		this.color=color;
		this.spaciousness=spaciousness;
		this.product=new ArrayList<Product>();
	} 
	public Basket(LocalDate timeOfPickUp,String color,int spaciousness,List<Product> newproducts) {
		this.timeOfPicingkUp=timeOfPickUp;
		this.color=color;
		this.spaciousness=spaciousness;
		this.product=newproducts;
	}

	
	@Override
	public boolean equals(Object obj) {
		if(obj==this) {
			return true;
		}
		if(obj.getClass()!=this.getClass()) {
			return false;
		}
		Basket p =(Basket)obj;
		return (this.spaciousness==p.spaciousness&&this.color.equals(p.color)&&this.timeOfPicingkUp.equals(p.timeOfPicingkUp));
	}
	@Override
	public String toString() {
		return String.format("This is %s basket of size: %s", this.color,this.spaciousness);
	}
	@Override
	public int hashCode() {
		return this.timeOfPicingkUp.hashCode()+this.color.hashCode();
	}
	public int getSpaciousness() {
		return this.spaciousness;
	}
	public void setSpaciousness(int newspaciousness) {
		this.spaciousness=newspaciousness;
	}
	public String getColor() {
		return this.color;
	}
	public void setColor(String newColor) {
		this.color=newColor;
	}
	public LocalDate gettime() {
		return this.timeOfPicingkUp;
	}
	
	
	
	public void setSetOfProducts (List<Product> newList) {
		this.product=newList;
	}
	public void addNewProducts(Product newProduct) {
		this.product.add(newProduct);
	}
	public int countSomeProduct(String productName) {
		int count=0;
		for(Product s : this.product) {
			if(s.getName().equals(productName)) {
				count++;
			}
		}
		return count;
	}
	public int getAmountOfPrdoucts() {
		return this.product.size();
	}
	public boolean removeProduct(String productName) {
		boolean flag=false;
		for(Product z: this.product) {
			if(z.getName().equals(productName)) {
				this.product.remove(z);
				flag=true;
			}
		}
		return flag;
	}
	
	
	
	
	
	
}
