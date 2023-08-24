
public class Address {
	//this  is the address class that i will be using when storing  a user's Adress.
	private String HouseNumber;
	private String Postcode;
	private String City;
	
	public Address(String HouseNumber, String Postcode, String City){
		this.HouseNumber = HouseNumber;
		this.Postcode = Postcode;
		this.City = City;
	}
	
	//make the getter methods
	public String getHouseNumber() {
		return HouseNumber;
	}
	public String getPostcode() {
		return Postcode;
	}
	public String getCity() {
		return City;
	}
	
	//make the setter methods
	//none needed
	
	@Override
	public String toString() {
		return HouseNumber + ", " + Postcode + ", " +City;
	}
	
}
