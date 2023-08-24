import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CustomerFileManager {
	
	public static ArrayList<Book> viewBooks() throws FileNotFoundException {
		//this method is just like the view books methdos in the AdminFileManager just sort sthe array lost differnetky 
		ArrayList<Book> booksInStock =  new ArrayList<Book>();
		Scanner bookFileScanner = new Scanner(new File("Stock.txt"));
		
		Book bk = null;
		
		while(bookFileScanner.hasNextLine()) {
			String[] bookInfo = bookFileScanner.nextLine().split(",");
			
			if (bookInfo[1].trim().equals("audiobook")) {
				
				String StrDate = bookInfo[5].trim();
				DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyy");
				LocalDate date = LocalDate.parse(StrDate, format);
				

				bk = new Audiobook(bookInfo[0].trim(),bookInfo[2].trim(), bookInfo[3].trim(),bookInfo[4].trim(),
						date,Integer.parseInt(bookInfo[6].trim()),Double.parseDouble(bookInfo[7].trim()),
						Double.parseDouble(bookInfo[8].trim()),AudioFormatType.valueOf(bookInfo[9].trim()));
				booksInStock.add(bk);
				
			} else if (bookInfo[1].trim().equals("ebook")) {
				
				String StrDate = bookInfo[5].trim();
				DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyy");
				LocalDate date = LocalDate.parse(StrDate, format);
				
				bk = new Ebook(bookInfo[0].trim(),bookInfo[2].trim(),bookInfo[3].trim(),bookInfo[4].trim(),
						date,Integer.parseInt(bookInfo[6].trim()),Double.parseDouble(bookInfo[7].trim()),
						Integer.parseInt(bookInfo[8].trim()),EbookFormatType.valueOf(bookInfo[9].trim()));
				
				booksInStock.add(bk);
			} else if (bookInfo[1].trim().equals("paperback")) {
				
				String StrDate = bookInfo[5].trim();
				DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyy");
				LocalDate date = LocalDate.parse(StrDate, format);
				
				bk = new Paperback(bookInfo[0].trim(),bookInfo[2].trim(),bookInfo[3].trim(),bookInfo[4].trim(),
						date,Integer.parseInt(bookInfo[6].trim()),Double.parseDouble(bookInfo[7].trim()),
						Integer.parseInt(bookInfo[8].trim()),bookInfo[9].trim());
				booksInStock.add(bk);
			}
			
		}
		bookFileScanner.close();
		
		
		//sort the array list
		Collections.sort(booksInStock, new PriceCompare());
		return booksInStock;
	}
	
	public static void updateStock(ArrayList<Book> NewStock) throws IOException {
		//this function just allows me to update the book file with a list of books that the user passes in to the method.
		FileWriter file = new FileWriter("Stock.txt");
		BufferedWriter bw = new BufferedWriter(file);
		for (Book bk : NewStock) {
			bw.write(bk.toString());
		}
		bw.close();
	}
}