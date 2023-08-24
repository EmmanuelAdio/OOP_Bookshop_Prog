import java.util.ArrayList;

public class ShoppingBasket {
	/*
	 * this is the class diagram for the shopping basket we will use this to make a basket.
	 */	
	private ArrayList<Book> Basket = new ArrayList<Book>();
	
	
	
	public void clearBasket() {
		/* this method is used to clear the contents of the array list Basket. */
		Basket.clear();
	}
	
	public void removeFromBasket(Book book) {
		/*
		 * this function was used to remove a book from the array list Basket
		 * */
		int index = -1;
		for (Book bk : Basket) {
			index++;
			if (bk.getBarcode() == book.getBarcode()) {
				Basket.remove(index);
				break;
			}
		}
	}
	
	public void addToBasket(Book book) {
		/* this function adds to the basket array list.*/
		Basket.add(book);
	}
	
	public void increaseBookQuantity(Book book) {
		/*
		 * this function increase the book quantity of a paassed book that is also in
		 * the Baket array List.
		 */		for(Book bk : this.Basket) {
			if(bk.getBarcode() == book.getBarcode()) {
				bk.setQuantity(bk.getQuantity() + 1);
			}
		}
		
	}
	
	public ArrayList<Book> getBasket() {
		//this fucntion gets and returns the Basket.
		return Basket;
	}
	

	
}
