package com.coderscampus.assignment4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Assignment4App {
	/* class variables and constants */
	static final int MAX_TRIES = 5;
	static final String WELCOME_MESSAGE = "Welcome: ";
	static final String EMAIL_PROMPT = "Enter your email:";
	static final String PASSWORD_PROMPT = "Enter your password:";
	static final String INVALID_PROMPT = "Invalid login, please try again";
	static final String LOCKOUT_MESSAGE = "Too many failed login attempts, your are now locked out.";
	static final String UPDATE_EMAIL_PROMPT = "Please type in your new username:";
	static final String UPDATE_PASSWORD_PROMPT = "Please type in your new password:";
	static final String UPDATE_NAME_PROMPT = "Please type in your new name:";
	static final String SUPERUSER_LOGIN_PROMPT = "Which user would you like to loging as? (Type in a valid username):";

	static final String OPTION_PROMPT = "Please choose from the following options:";
	static final String CHOOSE_NEW_USER = "(0) Log in as another user";
	static final String UPDATE_USERNAME = "(1) Update username";
	static final String UPDATE_PASSWORD = "(2) Update password";
	static final String UPDATE_NAME = "(3) Update name";
	static final String EXIT_OPTION = "(4) Exit";
	static final String INVALID_OPTION_PROMPT = "Invalid option, please try again";

	public static User[] users = new User[20]; //<
	public static Scanner scanner = new Scanner(System.in);

	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		UserService.loadUsersArray(users);
		/* Test to see if the User[] was properly loaded */
//		UserService.displayUserArray(users);
		
		int loginAttempts = 0;
//		boolean validLogin = false;
		User validatedUser = null;
		
		/* Log in user based on credentials found in users.txt file */
		
		while (validatedUser == null && loginAttempts < MAX_TRIES) {
			System.out.println(EMAIL_PROMPT);
			String username = scanner.nextLine();
			System.out.println(PASSWORD_PROMPT);
			String password = scanner.nextLine();
			validatedUser = UserService.validateUser(username, password);
			
			if (validatedUser != null) {
				System.out.println(WELCOME_MESSAGE + validatedUser.getName());
//				validLogin = true;
			} else {
				loginAttempts++;
				if (loginAttempts >= MAX_TRIES) {
					System.out.println(LOCKOUT_MESSAGE);
//					validLogin = false;
				} else {
					System.out.println(INVALID_PROMPT);
				} //end of inner else block
			} //end of outer else block
		} //end of while loop

		/* Display user options based on the type of user credentials that was used (normal_user vs super_user) */
		if (validatedUser != null) {
			int selectedOption = 9;
			while (selectedOption != 4) {
				selectedOption = displayUserOptions(validatedUser);
				switch (selectedOption) {
					case 0:
						if (validatedUser.getRole().equals("super_user")) {
						System.out.println("Option 0 selected");
						
						} else {
							System.out.println(INVALID_OPTION_PROMPT);
						}
						break;
					case 1:
						System.out.println("Option 1 selected");
						updateUsername(validatedUser);
//						UserService.displayUserArray();
						break;
					case 2:
						System.out.println("Option 2 selected");
						updatePassword(validatedUser);
//						UserService.displayUserArray();
						break;
					case 3:
						System.out.println("Option 3 selected");
						updateName(validatedUser);
//						UserService.displayUserArray();
						System.out.println(WELCOME_MESSAGE + validatedUser.getName());
						break;
					case 4:
						System.out.println("Option 4 selected");
						scanner.close();
						break;
					default:			
						System.out.println(INVALID_OPTION_PROMPT);
				
				} //end of switch block
				
			} //end of while loop
			
			/* sort and rewrite users.txt file */
			UserService.outputUserFile();					

					
			
			
		} //end of if (validatedUser) block
		
		System.out.println("end of main program");
	} //end of main method
	
	private static int displayUserOptions(User validatedUser) {
		System.out.println("----------");
		System.out.println(OPTION_PROMPT);
		if (validatedUser.getRole().equals("super_user")) {
			System.out.println(CHOOSE_NEW_USER);
		} //option 0 is only for super users
		System.out.println(UPDATE_USERNAME);
		System.out.println(UPDATE_PASSWORD);
		System.out.println(UPDATE_NAME);
		System.out.println(EXIT_OPTION);
		String option = scanner.nextLine();
		return Integer.parseInt(option);
	} //end of displayUserOptions method
	
	private static void updateUsername(User validatedUser) {
		System.out.println(UPDATE_EMAIL_PROMPT);
		String username = scanner.nextLine();
		validatedUser.setUsername(username);		
	} //end of updateUserName method
	
	private static void updatePassword(User validatedUser) {
		System.out.println(UPDATE_PASSWORD_PROMPT);
		String password = scanner.nextLine();
		validatedUser.setPassword(password);
	} //end of updatePassword method
	
	private static void updateName(User validatedUser) {
		System.out.println(UPDATE_NAME_PROMPT);
		String name = scanner.nextLine();
		validatedUser.setName(name);
	} //end of updateName method

} //end of Assignment4App
