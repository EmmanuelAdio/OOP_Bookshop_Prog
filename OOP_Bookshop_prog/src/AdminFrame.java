import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class AdminFrame extends JFrame {
	private Admin Admin;
	
	private JPanel contentPane;
	
	private JTabbedPane tabbedPane;
	private JPanel View_Books;
	private JPanel Add_New_Books;
	
	private JTable booktbl;
	private DefaultTableModel bookdtm = new  DefaultTableModel() {
		//this will stop the tables in my frame uneditable.
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JScrollPane scrollPane;
	
	private static AdminFileManager FileManager = new AdminFileManager();
	private static  ArrayList<Book> bookStock;
	
	private JTextField NewID;
	private JTextField NewTitle;
	private JComboBox NewTpye;
	private JLabel Genre_Label;
	private JLabel RD_Label;
	private JTextField NewRD;
	private JLabel Quantity_Label;
	private JTextField NewQuantity;
	private JLabel lblNewLabel;
	private JComboBox comboBox;
	private JComboBox AudioFormat;
	private JComboBox comboBox_1;
	private JTextField NewRP;
	private JTabbedPane tabbedPane_1;
	private JPanel Paperback_Tab;
	private JPanel Audiobook_Tab;
	private JPanel Ebook_Tab;
	private JTextField PaperbackPages;
	private JTextField AudioLength;
	private JTextField EbookPages;
	private JPanel panel;
	private JButton ExitBtn;
	private JButton EbookBtn;
	private JButton AudiobookBtn;
	private JButton PaperbackBtn;
	
	/**
	 * Launch the application.
	 * @throws FileNotFoundException 
	 */
	/**
	 * Create the frame.
	 * @param Admin 
	 * @throws IOException 
	 */
	public AdminFrame(Admin Admin) throws IOException {
		this.Admin = Admin;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1050, 700);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 29, 1038, 636);
		contentPane.add(tabbedPane);
		
		ExitBtn = new JButton("Exit");
		ExitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		ExitBtn.setBounds(939, 2, 89, 23);
		contentPane.add(ExitBtn);
		
		//this is the view books table.
		bookTable();
		
		try {
			bookStock = FileManager.viewBooks();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}				
		//put the contents of the bookstock array list into the table
		for (Book bk : bookStock) {
			Object[] rowData;
			if (bk instanceof Paperback) {
				Paperback displaybk = (Paperback) bk;
				rowData = new Object[] {displaybk.getBarcode(), "Paperback", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
						displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), 
						displaybk.getCondition(), "", ""};
				bookdtm.addRow(rowData);
			} else if(bk instanceof Audiobook){
				Audiobook displaybk = (Audiobook) bk;
				rowData = new Object[] {displaybk.getBarcode(), "Audiobook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
						displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), "", "",displaybk.getListeningLength(), 
						displaybk.getFormat()};
				bookdtm.addRow(rowData);
			} else if(bk instanceof Ebook){
				Ebook displaybk = (Ebook) bk;
				rowData = new Object[] {displaybk.getBarcode(), "Ebook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
						displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), "","", 
						displaybk.getFormat()};
				bookdtm.addRow(rowData);
			}
		}
		
		
	}
	
	public void bookTable() throws IOException {
		//create the view books tab for the admin user		
		View_Books = new JPanel();
		tabbedPane.addTab("View Books", null, View_Books, null);
		View_Books.setLayout(null);

		JButton refreshbtn = new JButton("Refresh");
		refreshbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clear the table
				bookdtm.setRowCount(0);
				try {
					bookStock = FileManager.viewBooks();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}		
				
				//put the contents of the bookstock array list into the table
				for (Book bk : bookStock) {
					Object[] rowData;
					if (bk instanceof Paperback) {
						Paperback displaybk = (Paperback) bk;
						rowData = new Object[] {displaybk.getBarcode(), "Paperback", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
								displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), 
								displaybk.getCondition(), "", ""};
						bookdtm.addRow(rowData);
					} else if(bk instanceof Audiobook){
						Audiobook displaybk = (Audiobook) bk;
						rowData = new Object[] {displaybk.getBarcode(), "Audiobook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
								displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), "", "",displaybk.getListeningLength(), 
								displaybk.getFormat()};
						bookdtm.addRow(rowData);
					} else if(bk instanceof Ebook){
						Ebook displaybk = (Ebook) bk;
						rowData = new Object[] {displaybk.getBarcode(), "Ebook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
								displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), "","", 
								displaybk.getFormat()};
						bookdtm.addRow(rowData);
					}
				}
			}
		});
		refreshbtn.setBounds(653, 568, 109, 23);
		View_Books.add(refreshbtn);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 28, 1000, 529);
		View_Books.add(scrollPane);
		
		booktbl = new JTable();
		scrollPane.setViewportView(booktbl);
		
		bookdtm.setColumnIdentifiers(new Object[] {"Barcode", "Type","Title", "Language", "Genre","Release Date",
				"Quantity", "Retail price", "No.pages", "Condition","Listening Length", "format"});
		
		booktbl.setModel(bookdtm);
		
		EbookBtn = new JButton("Ebook");
		EbookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clear the screen and reprint the contents to the table
				bookdtm.setRowCount(0);
				try {
					bookStock = FileManager.viewBooks();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}	
				
				for (Book bk : bookStock) {
					Object[] rowData;
					
					if(bk instanceof Ebook){
						Ebook displaybk = (Ebook) bk;
						rowData = new Object[] {displaybk.getBarcode(), "Ebook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
								displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), "","", 
								displaybk.getFormat()};
						bookdtm.addRow(rowData);
					}
				}
			}
		});
		EbookBtn.setBounds(517, 568, 126, 23);
		View_Books.add(EbookBtn);
		
		AudiobookBtn = new JButton("Audiobook");
		AudiobookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clear the screen and reprint the contents to the table
				bookdtm.setRowCount(0);
				try {
					bookStock = FileManager.viewBooks();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}		
				
				for (Book bk : bookStock) {
					Object[] rowData;
					if(bk instanceof Audiobook){
						Audiobook displaybk = (Audiobook) bk;
						rowData = new Object[] {displaybk.getBarcode(), "Audiobook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
								displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), "", "",displaybk.getListeningLength(), 
								displaybk.getFormat()};
						bookdtm.addRow(rowData);
					}
				}
			}
		});
		AudiobookBtn.setBounds(381, 568, 126, 23);
		View_Books.add(AudiobookBtn);
		
		PaperbackBtn = new JButton("Paperback");
		PaperbackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clear the screen and reprint the contents to the table
				bookdtm.setRowCount(0);
				try {
					bookStock = FileManager.viewBooks();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}		
				
				//put the contents of the bookstock array list into the table
				for (Book bk : bookStock) {
					Object[] rowData;
					if (bk instanceof Paperback) {
						Paperback displaybk = (Paperback) bk;
						rowData = new Object[] {displaybk.getBarcode(), "Paperback", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
								displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), 
								displaybk.getCondition(), "", ""};
						bookdtm.addRow(rowData);
					}
				}
			}
		});
		PaperbackBtn.setBounds(245, 568, 126, 23);
		View_Books.add(PaperbackBtn);
		
		
		////////// ADD NEW BOOK CODE //////////////
		
		////----Default Attributes----////
		Add_New_Books = new JPanel();
		tabbedPane.addTab("Add Books", null, Add_New_Books, null);
		Add_New_Books.setLayout(null);
		
		JLabel ID_Label = new JLabel("Barcode");
		ID_Label.setBounds(10, 10, 50, 14);
		Add_New_Books.add(ID_Label);
		NewID = new JTextField();
		NewID.setBounds(100, 10, 180, 20);
		Add_New_Books.add(NewID);
		NewID.setColumns(10);
			
		
		JLabel Title_Label = new JLabel("Title");
		Title_Label.setBounds(10, 40, 50, 14);
		Add_New_Books.add(Title_Label);
		
		NewTitle = new JTextField();
		NewTitle.setBounds(100, 40, 180, 20);
		Add_New_Books.add(NewTitle);
		NewTitle.setColumns(10);
		
		JLabel Language_Label = new JLabel("Language");
		Language_Label.setBounds(10, 80, 50, 14);
		Add_New_Books.add(Language_Label);
		
		String[] Languages = {"English","French"};
		JComboBox NewLanguage = new JComboBox(Languages);
		NewLanguage.setBounds(100, 80, 180, 20);
		Add_New_Books.add(NewLanguage);
		
		Genre_Label = new JLabel("Genre");
		Genre_Label.setBounds(10, 120, 50, 14);
		Add_New_Books.add(Genre_Label);
		
		String[] Genres = {"Politics","Business","Computer Science","Biography"};
		JComboBox NewGenre = new JComboBox(Genres);
		NewGenre.setBounds(100, 120, 180, 20);
		Add_New_Books.add(NewGenre);
		
		RD_Label = new JLabel("Release date");
		RD_Label.setBounds(10, 160, 80, 14);
		Add_New_Books.add(RD_Label);
		
		NewRD = new JTextField();
		NewRD.setBounds(100, 160, 180, 20);
		Add_New_Books.add(NewRD);
		NewRD.setColumns(10);
		
		Quantity_Label = new JLabel("Quantity");
		Quantity_Label.setBounds(10, 200, 50, 14);
		Add_New_Books.add(Quantity_Label);
		
		NewQuantity = new JTextField();
		NewQuantity.setBounds(100, 200, 180, 20);
		Add_New_Books.add(NewQuantity);
		NewQuantity.setColumns(10);
		
		JLabel NewRP_Label = new JLabel("Retail Price");
		NewRP_Label.setBounds(10, 240, 180, 14);
		Add_New_Books.add(NewRP_Label);
		
		NewRP = new JTextField();
		NewRP.setBounds(100, 240, 180, 20);
		Add_New_Books.add(NewRP);
		NewRP.setColumns(10);
		
		
		
		////----Specialised Books----////
		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(10, 271, 314, 319);
		Add_New_Books.add(tabbedPane_1);
		
		//--Paperback--//
		Paperback_Tab = new JPanel();
		tabbedPane_1.addTab("PaperBack", null, Paperback_Tab, null);
		Paperback_Tab.setLayout(null);
		
		JLabel Pages_Label_1 = new JLabel("No. of Pages");
		Pages_Label_1.setBounds(10, 11, 82, 14);
		Paperback_Tab.add(Pages_Label_1);
		
		PaperbackPages = new JTextField();
		PaperbackPages.setColumns(10);
		PaperbackPages.setBounds(90, 8, 180, 20);
		Paperback_Tab.add(PaperbackPages);
		
		JLabel Condition_Label = new JLabel("Condition");
		Condition_Label.setBounds(10, 45, 112, 14);
		Paperback_Tab.add(Condition_Label);
		
		String[] ConditionTypes = {"New","Used"};
		JComboBox NewCondition = new JComboBox(ConditionTypes);
		NewCondition.setBounds(90, 42, 180, 20);
		Paperback_Tab.add(NewCondition);
		
		JButton AddBook_Paperback = new JButton("Add Book(s)");
		AddBook_Paperback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//do our validation!!
				try {
					//check that the entered Barcode is Valid
					if (NewID.getText().trim().length() != 8){
						throw new IllegalArgumentException("You must enter a valid barCode of 8 charecters");
					}
					try {
						int test = Integer.parseInt(NewID.getText().trim());
					} catch (NumberFormatException e1){
						throw new NumberFormatException("You must enter only numbers for Barcode");
					}
					boolean inside = false;
					for(Book bk : bookStock) {
						if (bk.getBarcode().equals(NewID.getText())) {
							inside = true;
							break;
						}
					}
					if (inside) {
						throw new IllegalArgumentException("This Barcode is already used");
					}
					
					//check the entered title is valid
					if(NewTitle.getText() == null) {
						throw new IllegalArgumentException("You must enter a Title");
					}
					
					//check the Langues is valid
					if(NewLanguage.getSelectedItem().toString().trim() == null) {
						throw new IllegalArgumentException("You must Pick a Language");
					}
					
					//check the genre is valid
					if(NewGenre.getSelectedItem().toString().trim() == null) {
						throw new IllegalArgumentException("You must Pick a Genre");
					}
					//Check the date is valid
					try {
						SimpleDateFormat simpleFormat =new SimpleDateFormat("dd-MM-yyyy");
						simpleFormat.setLenient(false);
						
						Date date = simpleFormat.parse(NewRD.getText().trim());
					} catch (ParseException e1) {
						throw new IllegalArgumentException("You must Enter a valid Date (dd-mm-yyyy)");
					}
					
					//check the quantity is valid
					try {
						int test = Integer.parseInt(NewQuantity.getText().trim());
					} catch (NumberFormatException e1){
						throw new NumberFormatException("You must enter only numbers for Quantity");
					}
					
					//check the Retail price is valid.
					try {
						double test = Double.parseDouble(NewRP.getText().trim());
					} catch (NumberFormatException e1){
						throw new NumberFormatException("You must enter a valid Retail price");
					}
					
					//check the number of pages is valid
					try {
						int test = Integer.parseInt(PaperbackPages.getText().trim());
						if (test <= 0) {
							throw new NumberFormatException("You must enter a valid number of Pages");
						}
					} catch (NumberFormatException e1){
						throw new NumberFormatException("You must enter a valid number of Pages");
					}
					//check that the condition is valid
					if(NewCondition.getSelectedItem().toString().trim() == null) {
						throw new IllegalArgumentException("You must pick a Condition");
					}
					//creat teh new book as an object
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					Paperback NewBook = new Paperback(NewID.getText().trim(), NewTitle.getText().trim(), NewLanguage.getSelectedItem().toString().trim(),
							NewGenre.getSelectedItem().toString().trim(), LocalDate.parse(NewRD.getText().trim(),formatter), Integer.parseInt(NewQuantity.getText().trim()),
							Double.parseDouble(NewRP.getText().trim()), Integer.parseInt(PaperbackPages.getText().trim()), NewCondition.getSelectedItem().toString().trim());
					//add the new book to the file and update bookstock
					try {
						FileManager.addBook(NewBook);
						bookStock = FileManager.viewBooks();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//print out succes and error messages.
					JOptionPane.showMessageDialog(null, "New Audiobook Added", "Success:", JOptionPane.PLAIN_MESSAGE);
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR:", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		AddBook_Paperback.setBounds(10, 74, 118, 24);
		Paperback_Tab.add(AddBook_Paperback);
		
		
		//--Audiobook--//
		Audiobook_Tab = new JPanel();
		tabbedPane_1.addTab("Audiobook", null, Audiobook_Tab, null);
		Audiobook_Tab.setLayout(null);
		
		JLabel Length_Label = new JLabel("Listening Length");
		Length_Label.setBounds(10, 11, 112, 14);
		Audiobook_Tab.add(Length_Label);
		
		AudioLength = new JTextField();
		AudioLength.setColumns(10);
		AudioLength.setBounds(113, 8, 180, 20);
		Audiobook_Tab.add(AudioLength);
		
		JLabel AudioFormat_Label = new JLabel("Audio Format");
		AudioFormat_Label.setBounds(10, 37, 105, 21);
		Audiobook_Tab.add(AudioFormat_Label);
		
		JComboBox Audio = new JComboBox(AudioFormatType.values());
		Audio.setBounds(113, 36, 180, 22);
		Audiobook_Tab.add(Audio);
		
		JButton AddBook_Audiobook = new JButton("Add Book(s)");
		AddBook_Audiobook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Do the validation again same as before.
				try {
					if (NewID.getText().trim().length() != 8){
						throw new IllegalArgumentException("You must enter a valid barCode of 8 charecters");
					}
					try {
						int test = Integer.parseInt(NewID.getText().trim());
					} catch (NumberFormatException e1){
						throw new NumberFormatException("You must enter only numbers for Barcode");
					}
					boolean inside = false;
					for(Book bk : bookStock) {
						if (bk.getBarcode().equals(NewID.getText())) {
							inside = true;
							break;
						}
					}
					if (inside) {
						throw new IllegalArgumentException("This Barcode is already used");
					}
					if(NewTitle.getText() == null) {
						throw new IllegalArgumentException("You must enter a Title");
					}
					if(NewLanguage.getSelectedItem().toString().trim() == null) {
						throw new IllegalArgumentException("You must Pick a Language");
					}
					if(NewGenre.getSelectedItem().toString().trim() == null) {
						throw new IllegalArgumentException("You must Pick a Genre");
					}
					
					try {
						SimpleDateFormat simpleFormat =new SimpleDateFormat("dd-MM-yyyy");
						simpleFormat.setLenient(false);
						
						Date date = simpleFormat.parse(NewRD.getText().trim());
					} catch (ParseException e1) {
						throw new IllegalArgumentException("You must Enter a valid Date (dd-mm-yyyy)");
					}
					
					try {
						int test = Integer.parseInt(NewQuantity.getText().trim());
					} catch (NumberFormatException e1){
						throw new NumberFormatException("You must enter only numbers for Quantity");
					}
					
					try {
						double test = Double.parseDouble(NewRP.getText().trim());
					} catch (NumberFormatException e1){
						throw new NumberFormatException("You must enter a valid Retail price");
					}
					
					try {
						double test = Double.parseDouble(AudioLength.getText().trim());
					} catch (NumberFormatException e1){
						throw new NumberFormatException("You must enter a valid Audio Listening Length");
					}
					
					if(Audio.getSelectedItem().toString().trim() == null) {
						throw new IllegalArgumentException("You must pick an audio Format");
					}
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					
					Audiobook NewBook = new Audiobook(NewID.getText().trim(), NewTitle.getText().trim(), NewLanguage.getSelectedItem().toString().trim(),
							NewGenre.getSelectedItem().toString().trim(), LocalDate.parse(NewRD.getText().trim(),formatter), Integer.parseInt(NewQuantity.getText().trim()),
							Double.parseDouble(NewRP.getText().trim()), Double.parseDouble(AudioLength.getText().trim()), (AudioFormatType)Audio.getSelectedItem());
					
					try {
						FileManager.addBook(NewBook);
						bookStock = FileManager.viewBooks();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "New Audiobook Added", "Success:", JOptionPane.PLAIN_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR:", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		AddBook_Audiobook.setBounds(10, 78, 118, 24);
		Audiobook_Tab.add(AddBook_Audiobook);
		
		
		//--Ebook--//
		Ebook_Tab = new JPanel();
		tabbedPane_1.addTab("Ebook", null, Ebook_Tab, null);
		Ebook_Tab.setLayout(null);
		
		JLabel Pages_Label_2 = new JLabel("No. of Pages");
		Pages_Label_2.setBounds(10, 11, 82, 14);
		Ebook_Tab.add(Pages_Label_2);
		
		EbookPages = new JTextField();
		EbookPages.setColumns(10);
		EbookPages.setBounds(98, 8, 180, 20);
		Ebook_Tab.add(EbookPages);
		
		JLabel EbookFormat_Label_1 = new JLabel("Ebook Format");
		EbookFormat_Label_1.setBounds(10, 42, 112, 18);
		Ebook_Tab.add(EbookFormat_Label_1);
		
		JComboBox EbookFormat = new JComboBox(EbookFormatType.values());
		EbookFormat.setBounds(98, 39, 180, 24);
		Ebook_Tab.add(EbookFormat);
		
		JButton AddBook_Ebook = new JButton("Add Book(s)");
		AddBook_Ebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Do the validation again same as before.
				try {
					if (NewID.getText().trim().length() != 8){
						throw new IllegalArgumentException("You must enter a valid barCode of 8 charecters");
					}
					try {
						int test = Integer.parseInt(NewID.getText().trim());
					} catch (NumberFormatException e1){
						throw new NumberFormatException("You must enter only numbers for Barcode");
					}
					if(NewTitle.getText() == null) {
						throw new IllegalArgumentException("You must enter a Title");
					}
					boolean inside = false;
					for(Book bk : bookStock) {
						if (bk.getBarcode().equals(NewID.getText())) {
							inside = true;
							break;
						}
					}
					if (inside) {
						throw new IllegalArgumentException("This Barcode is already used");
					}
					if(NewLanguage.getSelectedItem().toString().trim() == null) {
						throw new IllegalArgumentException("You must Pick a Language");
					}
					if(NewGenre.getSelectedItem().toString().trim() == null) {
						throw new IllegalArgumentException("You must Pick a Genre");
					}
					try {
						SimpleDateFormat simpleFormat =new SimpleDateFormat("dd-MM-yyyy");
						simpleFormat.setLenient(false);
						
						Date date = simpleFormat.parse(NewRD.getText().trim());
					} catch (ParseException e1) {
						throw new IllegalArgumentException("You must Enter a valid Date (dd-mm-yyyy)");
					}
					
					try {
						int test = Integer.parseInt(NewQuantity.getText().trim());
					} catch (NumberFormatException e1){
						throw new NumberFormatException("You must enter only numbers for Quantity");
					}
					
					try {
						double test = Double.parseDouble(NewRP.getText().trim());
					} catch (NumberFormatException e1){
						throw new NumberFormatException("You must enter a valid Retail price");
					}
					
					try {
						int test = Integer.parseInt(EbookPages.getText().trim());
						if (test <= 0) {
							throw new NumberFormatException("You must enter a valid number of Pages");
						}
					} catch (NumberFormatException e1){
						throw new NumberFormatException("You must enter a valid number of Pages");
					}
					
					if(EbookFormat.getSelectedItem().toString().trim() == null) {
						throw new IllegalArgumentException("You must pick an audio Format");
					}
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					
					Ebook NewBook = new Ebook(NewID.getText().trim(), NewTitle.getText().trim(), NewLanguage.getSelectedItem().toString().trim(),
							NewGenre.getSelectedItem().toString().trim(), LocalDate.parse(NewRD.getText().trim(),formatter), Integer.parseInt(NewQuantity.getText().trim()),
							Double.parseDouble(NewRP.getText().trim()), Integer.parseInt(EbookPages.getText().trim()), (EbookFormatType)EbookFormat.getSelectedItem());
					
				
					try {
						FileManager.addBook(NewBook);
						bookStock = FileManager.viewBooks();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "New Audiobook Added", "Success:", JOptionPane.PLAIN_MESSAGE);
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR:", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		AddBook_Ebook.setBounds(10, 86, 118, 24);
		Ebook_Tab.add(AddBook_Ebook);
		
		panel = new JPanel();
		panel.setBounds(352, 10, 670, 580);
		Add_New_Books.add(panel);
	}
	
}
