import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends User{
	//this is the admin class the sub class of the user class.


	public Admin(String ID, String Username, String Surname, Address UserAddress) {
		super(ID, Username, Surname, UserAddress);
	}
	
	//getter methods already initialised in the superclass.
	//none needed
	
	@Override
	public String toString() {
		String string = this.getID() + ", " + this.getUsername() + ", " + this.getSurname() + ", " + this.getUserAddress().toString()
				+ ", ," + "admin" + "\n";
		return string;
	}
}
