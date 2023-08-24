import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public abstract class Book {
	private String Barcode;
	private String Title;
	private String Language;
	private String Genre;
	private LocalDate ReleaseDate;
	private int Quantity;
	private double RetailPrice;
	
	//Constructor
	public Book(String Barcode,String Title,String Language, String Genre, LocalDate ReleaseDate, int Quantity, double RetailPrice) {
		this.Barcode = Barcode;
		this.Title = Title;
		this.Language = Language;
		this.Genre = Genre;
		this.ReleaseDate = ReleaseDate;
		this.Quantity = Quantity;
		this.RetailPrice = RetailPrice;
				
	}

	//make the get methods
	public String getBarcode() {
		return Barcode;
	}
	public String getTitle() {
		return Title;
	}
	public String getLanguage() {
		return Language;
	}
	public String getGenre() {
		return Genre;
	}
	public String getDateString() {
		DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return ReleaseDate.format(form);
	}
	public LocalDate getDate() {
		return ReleaseDate;
	}
	public int getQuantity() {
		return Quantity;
	}
	public double getRetailPrice() {
		return RetailPrice;
	}
	
	//make the needed set classes
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	
	//the abstract method to string for the book class an its subclasses
	public abstract String toString();

}
