import java.time.LocalDate;
import java.util.*;

public class Paperback extends Book{
	private int NoOfPages;
	private String Condition;
	
	//Constructor
	public Paperback(String Barcode,String Title,String Language, String Genre, LocalDate ReleaseDate, int Quantity, 
			double RetailPrice, int NoOfPages, String Condition) {
		super(Barcode, Title, Language, Genre, ReleaseDate, Quantity, RetailPrice);
		this.NoOfPages = NoOfPages;
		this.Condition = Condition;
		
	}
	
	//Create the get methods
	public int getNoOfPages() {
		return NoOfPages;
	}
	public String getCondition() {
		return Condition;
	}
	
	@Override
	public String toString() {
		String stringBook = (this.getBarcode()) + ", " + "paperback" + ", " + this.getTitle() + ", " + this.getLanguage() + 
				", " + this.getGenre() + ", " + this.getDateString() + ", " + Integer.toString(this.getQuantity()) + ", " + 
				Double.toString(this.getRetailPrice()) + ", " + Integer.toString(this.getNoOfPages()) + ", "+ this.getCondition() + "\n";
		return stringBook;
	}

}