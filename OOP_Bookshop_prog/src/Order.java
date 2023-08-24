import java.util.ArrayList;

public class Order {
	private ArrayList<Book> items;
	private double balance;
	private Customer Customer;

	public Order(ArrayList<Book> books, Customer Customer) {
		this.items = books;
		this.Customer = Customer;
		
		double balance = 0;
		for (Book bk : items) {
			balance += bk.getQuantity()*bk.getRetailPrice();
		}
		this.balance = balance;
	}
	
	public ArrayList<Book> getItems() {
		return items;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String printOrder() {
		String order = "£"+this.getBalance()+" paid and your remaining credit balance is £"+(Customer.getCreditBalance() - this.getBalance())
				+". Your Address is "+ Customer.getUserAddress().toString();
		return order;
	}

}
