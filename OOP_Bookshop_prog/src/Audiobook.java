import java.time.LocalDate;
import java.util.*;

public class Audiobook extends Book{
	//this is the audiobook class that inherits the Book class. 
	private double ListeningLength;
	private AudioFormatType Format;
	
	//Constructor
	public Audiobook(String Barcode, String Title, String Language, String Genre, LocalDate Date, int Quantity, 
			double RetailPrice, double ListeningLength, AudioFormatType Format) {
		super(Barcode, Title, Language, Genre, Date, Quantity, RetailPrice);
		this.ListeningLength = ListeningLength;
		this.Format = Format;
	}
	
	//make the get methods
	public double getListeningLength() {
		return ListeningLength;
	}
	public AudioFormatType getFormat() {
		return Format;
	}
	
	@Override
	public String toString() {
		String stringBook = this.getBarcode() + ", " + "audiobook" + ", " + this.getTitle() + ", " + this.getLanguage() + 
				", " + this.getGenre() + ", " + this.getDateString() + ", " + Integer.toString(this.getQuantity()) + ", " + 
				Double.toString(this.getRetailPrice()) + ", " + this.getListeningLength() + ", "+ this.getFormat() + "\n";
		return stringBook;
	}
	
}
