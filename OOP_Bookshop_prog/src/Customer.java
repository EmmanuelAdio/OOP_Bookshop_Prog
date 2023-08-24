import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends User{
	// the customer class that is inherits the USer class and its methods
	private double CreditBalance;
	
	//customer constructor
	public Customer(String ID, String Username, String Surname, Address UserAddress,
			double CreditBalance) {
		super(ID, Username, Surname, UserAddress);
		this.CreditBalance = CreditBalance;
		
	}
	
	//getter methods
	public double getCreditBalance() {
		String temp = String.format("%.2f", CreditBalance);
		return Double.parseDouble(temp);
	}
	
	//setter method
	public void setCreditBalance(double creditBalance) {
		//this is to format my credit Balance.
		String temp = String.format("%.2f", creditBalance);
		creditBalance = Double.parseDouble(temp);
		CreditBalance = creditBalance;
	}

	@Override
	public String toString() {
		String string = this.getID() + ", " + this.getUsername() + ", " + this.getSurname() + ", " + this.getUserAddress().toString()
				+ ", " + this.getCreditBalance() + ", " + "customer" + "\n";
		return string;
	}

	

}
