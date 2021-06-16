package com.coderscampus.assignment4.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import com.coderscampus.assignment4.Assignment4App;
import com.coderscampus.assignment4.domain.SuperUser;
import com.coderscampus.assignment4.domain.User;

public class UserService {

	public static void loadUsersArray(User[] users) throws FileNotFoundException, IOException {
		String fileName = "./users.txt";
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = null;
			int i = 0;	//index for User array
			while ((line = reader.readLine()) != null) {
				String[] userData = line.split(", ");
				if (userData[3].equalsIgnoreCase("normal_user")) {
					/* load User object in User array */
					users[i] = new User(userData[0], userData[1], userData[2]);
				} else {
					/* load SuperUser object in User array */
					users[i] = new SuperUser(userData[0], userData[1], userData[2]);
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
	
	public static void outputUserFile() throws IOException {
		String fileName = "./users.txt";
		BufferedWriter writer = null;
		
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			// sort the Users array by role then by username
			Arrays.sort(Assignment4App.users);

			/* Test code to display contents of user[] object */
//			displayUserArray();
			
			/* Process the user array and update the users.txt file */
			for (User user : Assignment4App.users) {
				writer.write(user.writeOutput());
			} //end for-each block

		} //end of try block
		finally {
			if (writer != null)
				{ writer.close(); }			
		} //end of finally block		
	} //end of outputUserFile method
	
	public static void displayUserArray() {
		int i = 0;
		for (User user: Assignment4App.users) {			
			System.out.println(user.toString(i));
			i++;
		} //end of for each loop
	} //end of displayUserArray method
	
	public static User validateUser (String username, String password) {
		for (User user : Assignment4App.users) {
			if (user.getUsername().equalsIgnoreCase(username) &&
					user.getPassword().equals(password)) {
				return user;
			} //end if block
		} //end of for-each block
		return null;	//no match found
	} //end of validateUser method
	
	public static User swapToNewUser (String username) {
		for (User user : Assignment4App.users ) {
			if (user.getUsername().equalsIgnoreCase(username)) {
				return user; //match found
			} //end if block
		} //end of for-each block
		return null;	//no match found
	} //end of swapToNewUser method

} //end of UserService class
