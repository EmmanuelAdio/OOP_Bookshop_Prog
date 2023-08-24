import java.util.*;
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

public class UserFileManager {

	public static ArrayList<User> getUsers() throws FileNotFoundException{
		/*
		 * // the same as the CustomerFileManage and the Admin File manager classes buyt
		 * this one is used for the Users.
		 */		
		ArrayList<User> Users =  new ArrayList<User>();
		Scanner userFileScanner = new Scanner(new File("UserAccounts.txt"));
		
		User us = null;
		while(userFileScanner.hasNext()) {
			String[] userInfo = userFileScanner.nextLine().split(",");
			
			if (userInfo[7].trim().equals("admin")) {
				us = new Admin(userInfo[0].trim(), userInfo[1].trim(), userInfo[2].trim(),
						new Address( userInfo[3].trim(), userInfo[4].trim(), userInfo[5].trim()));
				Users.add(us);
			} else if(userInfo[7].trim().equals("customer")) {
				us = new Customer(userInfo[0].trim(), userInfo[1].trim(), userInfo[2].trim(),
						new Address( userInfo[3].trim(), userInfo[4].trim(), userInfo[5].trim()), 
						Double.parseDouble(userInfo[6].trim()));
				Users.add(us);
			}
		}
		return Users;
	}
	
	public static void updateUsers(ArrayList<User> NewUsers) throws IOException {
		/*
		 * just updateing and replacing the contents of the accounts file with the
		 * array list of users that was passed into the function.
		 */		
		FileWriter file = new FileWriter("UserAccounts.txt");
		BufferedWriter bw = new BufferedWriter(file);
		for (User us : NewUsers) {
			bw.write(us.toString());
		}
		bw.close();
	}
	
}
