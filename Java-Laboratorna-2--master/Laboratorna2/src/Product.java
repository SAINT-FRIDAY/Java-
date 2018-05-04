import java.util.Collections;
import java.util.Set;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Product implements Comparable<Product> {
	// private Set<Basket> baskets;
	private String name;
	private LocalDate dateOfCreation;
	private int shelfTimes;
	// private String type;

	public Product() {

	}

	/*
	 * public Product(String name,LocalDate dateoOfCreation,int shelfTime,String
	 * type,Set<Basket> baskets) { this.name=name;
	 * this.dateOfCreation=dateoOfCreation; this.ShelfTimes=shelfTime; }
	 */
	public Product(String name, LocalDate dateoOfCreation, int shelfTime) {
		this.name = name;
		this.dateOfCreation = dateoOfCreation;
		this.shelfTimes = shelfTime;
	}

	@Override
	public int compareTo(Product product) {
		return name.compareTo(product.name);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o.getClass() != this.getClass()) {
			return false;
		}
		Product d = (Product) o;
		return this.name.equals(d.name);
	}

	@Override
	public int hashCode() {
		// return this.name.hashCode()+this.dateOfCreation.toString().hashCode();
		return this.name.hashCode();
	}

	/*
	 * public Set<Basket> getsortedBaskets(){ return
	 * Collections.unmodifiableSet(baskets); }
	 */
	/*
	 * public boolean removeBasket(Basket basket) { return
	 * this.baskets.remove(basket); }
	 */
	/*
	 * public boolean addBasket(Basket basket) { if(!baskets.contains(basket)) {
	 * baskets.add(basket); return true; } else { return false; } }
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * public String getType() { return this.type; }
	 */
	public LocalDate getDateOfCreation() {
		return this.dateOfCreation;
	}

	public int getShelfTime() {
		return this.shelfTimes;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public void setDateOfCreation(LocalDate newDateOfCreation) {
		this.dateOfCreation = newDateOfCreation;
	}

	public void setShelfTimes(int newShelfTime) {
		this.shelfTimes = newShelfTime;
	}

	@Override
	public String toString() {
		return String.format("This is %s ,which was created on %s", this.name, this.dateOfCreation.toString());
	}

	public boolean isGood() {
		LocalDate temp;
		temp = this.dateOfCreation;
		temp = temp.plusMonths(shelfTimes);
		return (!temp.isBefore(LocalDate.now()));
	}

	public int howManyDaysLeft() {
		LocalDate temp;
		int days;
		temp = this.dateOfCreation;
		temp = temp.plusMonths(shelfTimes);
		if (!temp.isBefore(LocalDate.now())) {
			days = (int) ChronoUnit.DAYS.between(LocalDate.now(), temp);
		} else {
			days = 0;
		}
		return Math.abs(days);

	}

}
