import java.util.Comparator;

public class QuantityCompare implements Comparator<Book>{
	/*comparitor class that is used to allow the sorting of array lists by quantity.
	 * */
	public int compare(Book B1, Book B2) {
		if (B1.getQuantity() < B2.getQuantity()) return -1;
		if (B1.getQuantity() > B2.getQuantity()) return 1;
		return 0;
	}
}
