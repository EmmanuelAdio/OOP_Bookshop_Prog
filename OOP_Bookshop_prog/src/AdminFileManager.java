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

public class AdminFileManager {
	//this is the file manager class that will be using to handle changes to the file that is made by the Admin.
	
	public static ArrayList<Book> viewBooks() throws FileNotFoundException {
		/*
		 * This function viewBooks() is a function that i was going to use to collect all the books in the file and save them to an array list.
		 * 
		 * made it static for the error handling and to allow this method to not be limited to ts instance.
		 * */
		
		//make  a temp Array list that will store the books collected from the file.
		ArrayList<Book> booksInStock =  new ArrayList<Book>();
		//open a file scanner
		Scanner bookFileScanner = new Scanner(new File("Stock.txt"));
		
		Book bk = null;
		
		while(bookFileScanner.hasNextLine()) {
			String[] bookInfo = bookFileScanner.nextLine().split(",");
			
			//add the book to the array list of books depending on the book type we receive from the file.
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
		
		
		//sort the array list.
		Collections.sort(booksInStock, (new QuantityCompare()).reversed());
		return booksInStock;
	}
	
	public static void addBook(Book book) throws IOException{
		/*
		 * This function is what i will be useing to add books to the file.
		 * */
		ArrayList<Book> stock = AdminFileManager.viewBooks(); 
		stock.add(book);
		
		//write the temp Array list (stock) to tthe book file.
		FileWriter file = new FileWriter("Stock.txt");
		BufferedWriter bw = new BufferedWriter(file);
		for (Book bk : stock) {
			bw.write(bk.toString());
		}
		bw.close();
	}

}

