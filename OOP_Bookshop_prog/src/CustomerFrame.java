

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CustomerFrame extends JFrame {
	private Customer Customer;
	private Order CustomerOrder = new Order(shoppingBasket.getBasket(), Customer);
	
	private JPanel contentPane;
	private JTable booktbl;
	private JTable SearchTable;
	private JTable BasketTable;
	private DefaultTableModel bookdtm = new  DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private DefaultTableModel Searchdtm = new  DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private DefaultTableModel Basketdtm = new  DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JScrollPane scrollPane;
	
	private static CustomerFileManager FileManager = new CustomerFileManager();
	private static  ArrayList<Book> bookStock;
	private static  ArrayList<Book> bookStockDisplay;
	private static ShoppingBasket shoppingBasket = new ShoppingBasket();;
	private static ArrayList<User> Users;
	
	private JPanel View_Books;
	private JPanel Search_Panel;
	private JPanel Basket_panel;
	private JTextField SearchBarcode;
	private JButton OnlyPaperbackBtn;
	private JButton OnlyAudiobookBtn;
	private JButton OnlyEbookBtn;
	private JButton AllBooksBtn;
	private JButton AddToBasket;
	private JButton ClearBasket;
	private JButton CheckoutBtn;
	private JLabel lblNewLabel_1;
	private JTextField AudioLengthFilter;
	private JButton AudioLengthFilterBtn;
	private JButton ExitBtn;
	private JLabel BalanceLbl;
	private JLabel PriceLbl;
	
	

	/**
	 * Launch the application.
	 * @throws FileNotFoundException 
	 */

	/**
	 * Create the frame.
	 */
	public CustomerFrame(Customer Customer) {
		//This is the customer that has loged in
		this.Customer = (Customer) Customer;
		
		try {
			Users = UserFileManager.getUsers();
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR:", JOptionPane.ERROR_MESSAGE);
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1050, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 34, 1038, 631);
		contentPane.add(tabbedPane);
		
		
		//create the view book tab for the customer.
		View_Books = new JPanel();
		tabbedPane.addTab("All Books", null, View_Books, null);
		View_Books.setLayout(null);
		
		Search_Panel = new JPanel();
		tabbedPane.addTab("Search Books", null, Search_Panel, null);
		Search_Panel.setLayout(null);
		
		Basket_panel = new JPanel();
		tabbedPane.addTab("Basket", null, Basket_panel, null);
		Basket_panel.setLayout(null);
		
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
		ExitBtn.setBounds(939, 0, 89, 23);
		contentPane.add(ExitBtn);
		
		try {
			bookStock = FileManager.viewBooks();
			bookStockDisplay = FileManager.viewBooks();
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR:", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		ViewBooksContent();
		BookSearcingContent();
		BasketContent();
		
		//put the contents of the bookstock array list into the table
		for (Book bk : bookStockDisplay) {
			Object[] rowData;
			if (bk instanceof Paperback) {
				Paperback displaybk = (Paperback) bk;
				rowData = new Object[] {displaybk.getBarcode(), "Paperback", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
						displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), 
						displaybk.getCondition(), "", ""};
				bookdtm.addRow(rowData);
				Searchdtm.addRow(rowData);
			} else if(bk instanceof Audiobook){
				Audiobook displaybk = (Audiobook) bk;
				rowData = new Object[] {displaybk.getBarcode(), "Audiobook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
						displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), "", "",displaybk.getListeningLength(), 
						displaybk.getFormat()};
				bookdtm.addRow(rowData);
				Searchdtm.addRow(rowData);
			} else if(bk instanceof Ebook){
				Ebook displaybk = (Ebook) bk;
				rowData = new Object[] {displaybk.getBarcode(), "Ebook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
						displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), "","", 
						displaybk.getFormat()};
				bookdtm.addRow(rowData);
				Searchdtm.addRow(rowData);
			}
		}
		

	}
	
	public void ViewBooksContent() {
		JButton refreshbtn = new JButton("Refresh");
		refreshbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collections.copy(bookStock,bookStockDisplay);
				//this functionis just for the normal display of the books in file.
				bookdtm.setRowCount(0);
				
				//put the contents of the bookstock array list into the table
				for (Book bk : bookStockDisplay) {
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
		refreshbtn.setBounds(468, 578, 89, 23);
		View_Books.add(refreshbtn);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1000, 556);
		View_Books.add(scrollPane);
		
		booktbl = new JTable();
		scrollPane.setViewportView(booktbl);
		
		//decide to copy and paste here for quick solution should have mad e variable that i can store the column values.
		bookdtm.setColumnIdentifiers(new Object[] {"Barcode", "Type","Title", "Language", "Genre","Release Date",
				"Quantity", "Retail price", "No.pages", "Condition","Listening Length", "format"});
		
		booktbl.setModel(bookdtm);	
	}
	
	public void BookSearcingContent() {
		JLabel Search = new JLabel("Search : ");
		Search.setBounds(10, 21, 79, 14);
		Search_Panel.add(Search);
		
		SearchBarcode = new JTextField();
		SearchBarcode.setBounds(78, 18, 289, 20);
		Search_Panel.add(SearchBarcode);
		SearchBarcode.setColumns(10);
		
		JButton SearchBtn = new JButton("Search");
		SearchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clear the table
				//make a copy of the book stock contents into the bookstockdisplay.
				Collections.copy(bookStock,bookStockDisplay);
				
				try {
					if (SearchBarcode.getText().length() != 8) {
						throw new IllegalArgumentException("You must enter a valid barCode of 8 charecters");
					}
					Searchdtm.setRowCount(0);
					for (Book bk : bookStockDisplay) {
						Object[] rowData;
						if (bk.getBarcode().equals(SearchBarcode.getText())) {
							if (bk instanceof Paperback) {
							Paperback displaybk = (Paperback) bk;
							rowData = new Object[] {displaybk.getBarcode(), "Paperback", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
									displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), 
									displaybk.getCondition(), "", ""};
							Searchdtm.addRow(rowData);
							} else if(bk instanceof Audiobook){
								Audiobook displaybk = (Audiobook) bk;
								rowData = new Object[] {displaybk.getBarcode(), "Audiobook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
										displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), "", "",displaybk.getListeningLength(), 
										displaybk.getFormat()};
								Searchdtm.addRow(rowData);
							} else if(bk instanceof Ebook){
								Ebook displaybk = (Ebook) bk;
								rowData = new Object[] {displaybk.getBarcode(), "Ebook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
										displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), "","", 
										displaybk.getFormat()};
								Searchdtm.addRow(rowData);
							}
							break;
						}
					}
				} catch (Exception e1) {
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR:", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		SearchBtn.setBounds(377, 17, 89, 23);
		Search_Panel.add(SearchBtn);
		
		JLabel lblNewLabel = new JLabel("Filter Type :");
		lblNewLabel.setBounds(10, 56, 79, 14);
		Search_Panel.add(lblNewLabel);
		
		OnlyPaperbackBtn = new JButton("Paperback");
		OnlyPaperbackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clear the table
				Collections.copy(bookStock,bookStockDisplay);
				
				Searchdtm.setRowCount(0);
				
				//put the contents of the bookstock array list into the table
				for (Book bk : bookStockDisplay) {
					Object[] rowData;
					if (bk instanceof Paperback) {
						Paperback displaybk = (Paperback) bk;
						rowData = new Object[] {displaybk.getBarcode(), "Paperback", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
								displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), 
								displaybk.getCondition(), "", ""};
						Searchdtm.addRow(rowData);
					}
				}
								
			}
		});
		OnlyPaperbackBtn.setBounds(88, 50, 89, 23);
		Search_Panel.add(OnlyPaperbackBtn);
		
		OnlyAudiobookBtn = new JButton("Audiobook");
		OnlyAudiobookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clear the table
				Collections.copy(bookStock,bookStockDisplay);
				
				Searchdtm.setRowCount(0);
				
				for (Book bk : bookStockDisplay) {
					Object[] rowData;
					if(bk instanceof Audiobook){
						Audiobook displaybk = (Audiobook) bk;
						rowData = new Object[] {displaybk.getBarcode(), "Audiobook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
								displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), "", "",displaybk.getListeningLength(), 
								displaybk.getFormat()};
						Searchdtm.addRow(rowData);
					}
				}
			}
		});
		OnlyAudiobookBtn.setBounds(199, 50, 89, 23);
		Search_Panel.add(OnlyAudiobookBtn);
		
		OnlyEbookBtn = new JButton("Ebook");
		OnlyEbookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clear the table
				Collections.copy(bookStock,bookStockDisplay);

				Searchdtm.setRowCount(0);
				
				for (Book bk : bookStockDisplay) {
					Object[] rowData;
					
					if(bk instanceof Ebook){
						Ebook displaybk = (Ebook) bk;
						rowData = new Object[] {displaybk.getBarcode(), "Ebook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
								displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), "","", 
								displaybk.getFormat()};
						Searchdtm.addRow(rowData);
					}
				}
			}
		});
		OnlyEbookBtn.setBounds(309, 50, 89, 23);
		Search_Panel.add(OnlyEbookBtn);
		
		AllBooksBtn = new JButton("All Books");
		AllBooksBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collections.copy(bookStock,bookStockDisplay);

				Searchdtm.setRowCount(0);
				
				//put the contents of the bookstock array list into the table
				for (Book bk : bookStockDisplay) {
					Object[] rowData;
					if (bk instanceof Paperback) {
						Paperback displaybk = (Paperback) bk;
						rowData = new Object[] {displaybk.getBarcode(), "Paperback", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
								displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), 
								displaybk.getCondition(), "", ""};
						Searchdtm.addRow(rowData);
					} else if(bk instanceof Audiobook){
						Audiobook displaybk = (Audiobook) bk;
						rowData = new Object[] {displaybk.getBarcode(), "Audiobook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
								displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), "", "",displaybk.getListeningLength(), 
								displaybk.getFormat()};
						Searchdtm.addRow(rowData);
					} else if(bk instanceof Ebook){
						Ebook displaybk = (Ebook) bk;
						rowData = new Object[] {displaybk.getBarcode(), "Ebook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
								displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), "","", 
								displaybk.getFormat()};
						Searchdtm.addRow(rowData);
					}
				}
			}
		});
		AllBooksBtn.setBounds(408, 51, 103, 20);
		Search_Panel.add(AllBooksBtn);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 77, 1010, 479);
		Search_Panel.add(scrollPane_1);
		
		SearchTable = new JTable();
		scrollPane_1.setViewportView(SearchTable);
		
		Searchdtm.setColumnIdentifiers(new Object[] {"Barcode", "Type","Title", "Language", "Genre","Release Date",
				"Quantity", "Retail price", "No.pages", "Condition","Listening Length", "format"});
		
		SearchTable.setModel(Searchdtm);
		
		lblNewLabel_1 = new JLabel("Minimum Audio Book Listening Length");
		lblNewLabel_1.setBounds(10, 567, 228, 19);
		Search_Panel.add(lblNewLabel_1);
		
		AudioLengthFilter = new JTextField();
		AudioLengthFilter.setBounds(264, 567, 187, 20);
		Search_Panel.add(AudioLengthFilter);
		AudioLengthFilter.setColumns(10);
		
		AudioLengthFilterBtn = new JButton("Filter");
		AudioLengthFilterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collections.copy(bookStock,bookStockDisplay);
				
				try {
					//perform the validation on the 
					if ((AudioLengthFilter.getText().length() <= 0)) {
						throw new NumberFormatException("Please enter a valid Audio listening length.");
					}
					
					Searchdtm.setRowCount(0);
					
					for (Book bk : bookStockDisplay) {
						Object[] rowData;
						if(bk instanceof Audiobook){
							Audiobook displaybk = (Audiobook) bk;
							if (displaybk.getListeningLength() >= Double.parseDouble(AudioLengthFilter.getText())) {
								rowData = new Object[] {displaybk.getBarcode(), "Audiobook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
										displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), "", "",displaybk.getListeningLength(), 
										displaybk.getFormat()};
								Searchdtm.addRow(rowData);
							}
						}
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR:", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		AudioLengthFilterBtn.setBounds(461, 567, 89, 23);
		Search_Panel.add(AudioLengthFilterBtn);
		
		AddToBasket = new JButton("Add To Basket");
		AddToBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collections.copy(bookStock,bookStockDisplay);
				
				try {
					//get the the row that was selected by the user.
					int selectedRow = SearchTable.getSelectedRow();
					
					if (selectedRow == -1) {
						throw new IndexOutOfBoundsException("Please select something before trying to add to basket");
					}
					//check what kind of book has been selected!
					//add the book that was selected to the shopping basket
					for (Book bk : bookStock) {
						if (bk.getBarcode().equals(SearchTable.getValueAt(selectedRow, 0))) {
							//add the book that was selected to the shopping basket
							//chek that the quantity of the book in stock is not 0
							if (bk.getQuantity() <= 1) {
								throw new Exception("This book has run out in stock. Pick a differnt book.");
							}
							
							boolean inside = false;
							
							//check if the book being added to the shopping basket is already in the shopping basket.
							for(Book bkInBasket : shoppingBasket.getBasket()) {
								if(bkInBasket.getBarcode() == bk.getBarcode()) {
									inside = true;
									shoppingBasket.increaseBookQuantity(bkInBasket);
									bk.setQuantity(bk.getQuantity() - 1);
									break;
								}
							}
							if (!inside) {
								if (bk instanceof Paperback) {
									Paperback bktemp = (Paperback) bk;
									Paperback AddedBook = new Paperback(bktemp.getBarcode(), bktemp.getTitle(), bktemp.getLanguage(),
											bktemp.getGenre(),bktemp.getDate(), bktemp.getQuantity(), bktemp.getRetailPrice(), bktemp.getNoOfPages(), bktemp.getCondition());
									AddedBook.setQuantity(1);
									shoppingBasket.addToBasket(AddedBook);
									
									bk.setQuantity(bk.getQuantity() - 1);

									break;
								} else if(bk instanceof Audiobook){
									Audiobook bktemp = (Audiobook) bk;
									Audiobook AddedBook = new Audiobook(bktemp.getBarcode(), bktemp.getTitle(), bktemp.getLanguage(),
											bktemp.getGenre(),bktemp.getDate(), bktemp.getQuantity(), bktemp.getRetailPrice(), bktemp.getListeningLength(),
											bktemp.getFormat());
									AddedBook.setQuantity(1);
									shoppingBasket.addToBasket(AddedBook);
									
									bk.setQuantity(bk.getQuantity() - 1);

									break;
								} else if(bk instanceof Ebook){
									Ebook bktemp = (Ebook) bk;
									Ebook AddedBook = new Ebook(bktemp.getBarcode(), bktemp.getTitle(), bktemp.getLanguage(),
											bktemp.getGenre(),bktemp.getDate(), bktemp.getQuantity(), bktemp.getRetailPrice(),bktemp.getNoOfPages(), 
											bktemp.getFormat());
									AddedBook.setQuantity(1);
									shoppingBasket.addToBasket(AddedBook);
									
									bk.setQuantity(bk.getQuantity() - 1);

									break;
								}
							}
							break;						
						}
					}
					
					Searchdtm.setRowCount(0);
					for (Book bk : bookStockDisplay) {
						Object[] rowData;
						if (bk instanceof Paperback) {
							Paperback displaybk = (Paperback) bk;
							rowData = new Object[] {displaybk.getBarcode(), "Paperback", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
									displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), 
									displaybk.getCondition(), "", ""};
							Searchdtm.addRow(rowData);
						} else if(bk instanceof Audiobook){
							Audiobook displaybk = (Audiobook) bk;
							rowData = new Object[] {displaybk.getBarcode(), "Audiobook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
									displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), "", "",displaybk.getListeningLength(), 
									displaybk.getFormat()};
							Searchdtm.addRow(rowData);
						} else if(bk instanceof Ebook){
							Ebook displaybk = (Ebook) bk;
							rowData = new Object[] {displaybk.getBarcode(), "Ebook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
									displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), "","", 
									displaybk.getFormat()};
							Searchdtm.addRow(rowData);
						}
					}
					

					Basketdtm.setRowCount(0);
					//put the contents of the shopping basket into the basket table
					for (Book bk : shoppingBasket.getBasket()) {
						Object[] rowData;
						if (bk instanceof Paperback) {
							Paperback displaybk = (Paperback) bk;
							rowData = new Object[] {displaybk.getBarcode(), "Paperback", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
									displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), 
									displaybk.getCondition(), "", ""};
							Basketdtm.addRow(rowData);
						} else if(bk instanceof Audiobook){
							Audiobook displaybk = (Audiobook) bk;
							rowData = new Object[] {displaybk.getBarcode(), "Audiobook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
									displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), "", "",displaybk.getListeningLength(), 
									displaybk.getFormat()};
							Basketdtm.addRow(rowData);
						} else if(bk instanceof Ebook){
							Ebook displaybk = (Ebook) bk;
							rowData = new Object[] {displaybk.getBarcode(), "Ebook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
									displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), "","", 
									displaybk.getFormat()};
							Basketdtm.addRow(rowData);
						}
					}
					CustomerOrder = new Order(shoppingBasket.getBasket(), Customer);
					PriceLbl.setText(" Your Total : £" + CustomerOrder.getBalance());
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR:", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		AddToBasket.setBounds(877, 567, 136, 23);
		Search_Panel.add(AddToBasket);
		
	}

	public void BasketContent() {
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 43, 1010, 506);
		Basket_panel.add(scrollPane_2);
		
		BasketTable = new JTable();
		scrollPane_2.setViewportView(BasketTable);
		
		Basketdtm.setColumnIdentifiers(new Object[] {"Barcode", "Type","Title", "Language", "Genre","Release Date",
				"Quantity", "Retail price", "No.pages", "Condition","Listening Length", "format"});
		
		BasketTable.setModel(Basketdtm);
		
		ClearBasket = new JButton("ClearBasket");
		ClearBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//put all books back into the stock
				for (Book bk : shoppingBasket.getBasket()) {
					for (Book bkInStock : bookStock) {
						if (bk.getBarcode() == bkInStock.getBarcode()) {
							bkInStock.setQuantity(bkInStock.getQuantity() + bk.getQuantity());
						}
					}
				}
				shoppingBasket.clearBasket();
				
				Basketdtm.setRowCount(0);
				
				Searchdtm.setRowCount(0);
				
				for (Book bk : bookStock) {
					//System.out.println("here");
					Object[] rowData;
					if (bk instanceof Paperback) {
						Paperback displaybk = (Paperback) bk;
						rowData = new Object[] {displaybk.getBarcode(), "Paperback", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
								displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), 
								displaybk.getCondition(), "", ""};
						Searchdtm.addRow(rowData);
					} else if(bk instanceof Audiobook){
						Audiobook displaybk = (Audiobook) bk;
						rowData = new Object[] {displaybk.getBarcode(), "Audiobook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
								displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), "", "",displaybk.getListeningLength(), 
								displaybk.getFormat()};
						Searchdtm.addRow(rowData);
					} else if(bk instanceof Ebook){
						Ebook displaybk = (Ebook) bk;
						rowData = new Object[] {displaybk.getBarcode(), "Ebook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
								displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), "","", 
								displaybk.getFormat()};
						Searchdtm.addRow(rowData);
					}
				}
				CustomerOrder = new Order(shoppingBasket.getBasket(), Customer);
				PriceLbl.setText(" Your Total : £" + CustomerOrder.getBalance());
			}
		});
		ClearBasket.setBounds(10, 560, 105, 23);
		Basket_panel.add(ClearBasket);
		
		CheckoutBtn = new JButton("Checkout");
		CheckoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerOrder = new Order(shoppingBasket.getBasket(), Customer);
				
				double remainder = (Customer.getCreditBalance() - CustomerOrder.getBalance());
				if (remainder < 0) {
					JOptionPane.showMessageDialog(null, "Not Enough Credit", "ERROR:", JOptionPane.ERROR_MESSAGE);
				} else {
					//update the actual stock of books
					try {
						CustomerFileManager.updateStock(bookStock);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR:", JOptionPane.ERROR_MESSAGE);
					}
					
					//make necessary changes to the user file
					for(User us : Users) {
						if (us.getID().equals(Customer.getID())) {
							if (us instanceof Customer) {
								Customer cUs = (Customer) us;
								(cUs).setCreditBalance(remainder);
								
								Customer = cUs;
								break;
							}
						}
					}
					
					try {
						UserFileManager.updateUsers(Users);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR:", JOptionPane.ERROR_MESSAGE);
					}
					
					JOptionPane.showMessageDialog(null, CustomerOrder.printOrder(), "Success:", JOptionPane.PLAIN_MESSAGE);

					Basketdtm.setRowCount(0);
					shoppingBasket.clearBasket();
					CustomerOrder = new Order(shoppingBasket.getBasket(), Customer);
					PriceLbl.setText(" Your Total : £" + CustomerOrder.getBalance());
					
					
					BalanceLbl.setText(" Your Credit : £" + Customer.getCreditBalance());
				}
			}
		});
		CheckoutBtn.setBounds(934, 560, 89, 23);
		Basket_panel.add(CheckoutBtn);
		
		JButton RemoveBookBtn = new JButton("Remove Book");
		RemoveBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					int selectedRow = BasketTable.getSelectedRow();
					
					if (selectedRow == -1) {
						throw new IndexOutOfBoundsException("Please select someting that you want to remove.");
					}
					
					for (Book bk : bookStock) {
						if (bk.getBarcode().equals(BasketTable.getValueAt(selectedRow, 0).toString())) {
							bk.setQuantity(bk.getQuantity() + Integer.parseInt(BasketTable.getValueAt(selectedRow, 6).toString()));
							shoppingBasket.removeFromBasket(bk);
							break;
						}
					}
					
					Basketdtm.setRowCount(0);
					//put the contents of the shopping basket into the basket table
					for (Book bk : shoppingBasket.getBasket()) {
						Object[] rowData;
						if (bk instanceof Paperback) {
							Paperback displaybk = (Paperback) bk;
							rowData = new Object[] {displaybk.getBarcode(), "Paperback", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
									displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), 
									displaybk.getCondition(), "", ""};
							Basketdtm.addRow(rowData);
						} else if(bk instanceof Audiobook){
							Audiobook displaybk = (Audiobook) bk;
							rowData = new Object[] {displaybk.getBarcode(), "Audiobook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
									displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), "", "",displaybk.getListeningLength(), 
									displaybk.getFormat()};
							Basketdtm.addRow(rowData);
						} else if(bk instanceof Ebook){
							Ebook displaybk = (Ebook) bk;
							rowData = new Object[] {displaybk.getBarcode(), "Ebook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
									displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), "","", 
									displaybk.getFormat()};
							Basketdtm.addRow(rowData);
						}
					}
					
					Searchdtm.setRowCount(0);
					
					//put the contents of the bookstock array list into the table
					for (Book bk : bookStockDisplay) {
						Object[] rowData;
						if (bk instanceof Paperback) {
							Paperback displaybk = (Paperback) bk;
							rowData = new Object[] {displaybk.getBarcode(), "Paperback", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
									displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), 
									displaybk.getCondition(), "", ""};
							Searchdtm.addRow(rowData);
						} else if(bk instanceof Audiobook){
							Audiobook displaybk = (Audiobook) bk;
							rowData = new Object[] {displaybk.getBarcode(), "Audiobook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
									displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), "", "",displaybk.getListeningLength(), 
									displaybk.getFormat()};
							Searchdtm.addRow(rowData);
						} else if(bk instanceof Ebook){
							Ebook displaybk = (Ebook) bk;
							rowData = new Object[] {displaybk.getBarcode(), "Ebook", displaybk.getTitle(), displaybk.getLanguage(), displaybk.getGenre(), 
									displaybk.getDate(), displaybk.getQuantity(), displaybk.getRetailPrice(), displaybk.getNoOfPages(), "","", 
									displaybk.getFormat()};
							Searchdtm.addRow(rowData);
						}
					}
					
					CustomerOrder = new Order(shoppingBasket.getBasket(), Customer);
					PriceLbl.setText(" Your Total : £" + CustomerOrder.getBalance());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR:", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		RemoveBookBtn.setBounds(125, 560, 118, 23);
		Basket_panel.add(RemoveBookBtn);
		
		
		BalanceLbl = new JLabel("New label");
		BalanceLbl.setBounds(10, 11, 224, 23);
		Basket_panel.add(BalanceLbl);
		
		BalanceLbl.setText(" Your Credit : £" + Customer.getCreditBalance());
		
		PriceLbl = new JLabel("New label");
		PriceLbl.setBounds(777, 564, 147, 19);
		Basket_panel.add(PriceLbl);
		
		PriceLbl.setText(" Your Total : £" + CustomerOrder.getBalance());
		
	}
}
