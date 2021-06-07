package com.coderscampus.assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UserService {

	public static void loadUsersArray(User[] users) throws FileNotFoundException, IOException {
		String fileName = "users.txt";
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = null;
			int i = 0;	//index for User array
			while ((line = reader.readLine()) != null) {
//				users[i] = new User(line.split(", "));
				String[] userData = line.split(", ");
				if (userData[3].equalsIgnoreCase("normal_user")) {
					users[i] = new User(userData[0],
										userData[1],
										userData[2]); //load User object in User array
				} else {
					users[i] = new SuperUser(userData[0],
											 userData[1],
											 userData[2]); //load SuperUser object in User array
				} //end if-else block
				

				/* Test code to display contents of user[] object */
//				System.out.println(users[i]);
				
				i++;	//increment index to next User array item
			} //end of while loop
		} //end of try block
		finally {
			if (reader != null)
				{ reader.close(); }
		} //end of finally block
	} //end of loadUsersArray method
	
	public static void displayUserArray(User[] users) {
		int i = 0;
		for (User user: users) {			
			System.out.println(user.toString(i));
			i++;
		} //end of for each loop
	} //end of displayUserArray
	
	public static User validateUser (String username, String password) {
		for (User user : Assignment4App.users) {
			if (user.getUsername().equalsIgnoreCase(username) &&
					user.getPassword().equals(password)) {
				return user;
			} //end if block
		} //end of for-each block
		return null;	//no match found
	} //end of validateUser

} //end of UserService class
