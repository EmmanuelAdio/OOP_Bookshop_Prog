import java.util.Comparator;

public class PriceCompare implements Comparator<Book>{
	/*comparitor class that is used to allow the sorting of array lists by Retail price.
	 * */
	public int compare(Book B1, Book B2) {
		if (B1.getRetailPrice() < B2.getRetailPrice()) return -1;
		if (B1.getRetailPrice() > B2.getRetailPrice()) return 1;
		return 0;
	}
}
