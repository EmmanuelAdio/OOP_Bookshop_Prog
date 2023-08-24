import java.time.LocalDate;
import java.util.*;

public class Ebook extends Book{
	private int NoOfPages;
	private EbookFormatType Format;
	
	//Constructor
	public Ebook(String Barcode,String Title,String Language, String Genre, LocalDate Date, int Quantity, double RetailPrice, 
			int NoOfPages, EbookFormatType Format) {
		super(Barcode, Title, Language, Genre, Date, Quantity, RetailPrice);
		this.NoOfPages = NoOfPages;
		this.Format = Format;
	}
	

	//create the get methods
	public int getNoOfPages() {
		return NoOfPages;
	}
	public EbookFormatType getFormat() {
		return Format;
	}
	
	@Override
	public String toString() {
		String stringBook = this.getBarcode() + ", " + "ebook" + ", " + this.getTitle() + ", " + this.getLanguage() + 
				", " + this.getGenre() + ", " + this.getDateString() + ", " + Integer.toString(this.getQuantity()) + ", " + 
				Double.toString(this.getRetailPrice()) + ", " + Integer.toString(this.getNoOfPages()) + ", "+ this.getFormat() + "\n";
		return stringBook;
	}

}
