import java.io.FileNotFoundException;

import java.util.ArrayList;


public abstract class User {
	private String ID;
	private String Username;
	private String Surname;
	private Address UserAddress;
	
	
	//constructor
	public User(String ID, String Username, String Surname, Address UserAddress) {
		this.ID = ID;
		this.Username = Username;
		this.Surname = Surname;
		this.UserAddress = UserAddress;
	}
	
	//the getter methods
	public String getID() {
		return ID;
	}
	public String getUsername() {
		return Username;
	}
	public String getSurname() {
		return Surname;
	}
	public Address getUserAddress() {
		return UserAddress;
	}
	
	//abstract toString method
	public abstract String toString();
	

}
