package com.coderscampus.assignment4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Assignment4App {
	/* class variables and constants */
	public static final int MAX_TRIES = 5;
	public static final String WELCOME_MESSAGE = "Welcome: ";
	public static final String EMAIL_PROMPT = "Enter your email:";
	public static final String PASSWORD_PROMPT = "Enter your password:";
	public static final String INVALID_PROMPT = "Invalid login, please try again.";
	public static final String LOCKOUT_MESSAGE = "Too many failed login attempts, your are now locked out.";
	public static final String UPDATE_EMAIL_PROMPT = "Please type in your new username:";
	public static final String USER_NOT_FOUND_MESSAGE = "Invalid username.";
	public static final String UPDATE_PASSWORD_PROMPT = "Please type in your new password:";
	public static final String UPDATE_NAME_PROMPT = "Please type in your new name:";
	public static final String SUPERUSER_LOGIN_PROMPT = "Which user would you like to loging as? (Type in a valid username):";

	public static final String OPTION_PROMPT = "Please choose from the following options:";
	public static final String CHOOSE_NEW_USER = "(0) Log in as another user";
	public static final String UPDATE_USERNAME = "(1) Update username";
	public static final String UPDATE_PASSWORD = "(2) Update password";
	public static final String UPDATE_NAME = "(3) Update name";
	public static final String EXIT_OPTION = "(4) Exit";
	public static final String INVALID_OPTION_PROMPT = "Invalid option, please try again";

	public static User[] users = new User[20];
	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		UserService.loadUsersArray(users);
		/* Test to see if the User[] was properly loaded */
//		UserService.displayUserArray(users);
		
		int loginAttempts = 0;
		User validatedUser = null;
		
		/* Log in user based on credentials found in users.txt file */
		while (validatedUser == null && loginAttempts < MAX_TRIES) {
			System.out.println(EMAIL_PROMPT);
			String username = scanner.nextLine();
			System.out.println(PASSWORD_PROMPT);
			String password = scanner.nextLine();
			validatedUser = UserService.validateUser(username, password);
			
			if (validatedUser == null) {
				loginAttempts++;
				if (loginAttempts >= MAX_TRIES) {
					System.out.println(LOCKOUT_MESSAGE);
				} else {
					System.out.println(INVALID_PROMPT);
				} //end of else block
			} //end of outer if block
		} //end of while loop

		/* Display user options based on the type of user credentials that was used (normal_user vs super_user) */
		if (validatedUser != null) {
			int selectedOption = 0;
			while (selectedOption != 4) {
				System.out.println(WELCOME_MESSAGE + validatedUser.getName());
				selectedOption = displayUserOptions(validatedUser);
				switch (selectedOption) {
					case 0:
						if (validatedUser.getRole().equals("super_user")) {
							System.out.println("Option 0 selected");
							System.out.println(SUPERUSER_LOGIN_PROMPT);
							String username = scanner.nextLine();
							User newUser = UserService.swapToNewUser(username);
							if (newUser != null) {
								validatedUser = newUser;
							} else {
								System.out.println(USER_NOT_FOUND_MESSAGE);
							}
						} else {
							System.out.println(INVALID_OPTION_PROMPT);
						}
						break;
					case 1:
						System.out.println("Option 1 selected");
						updateUsername(validatedUser);
						/* Test to see if the User[] was properly loaded */
//						UserService.displayUserArray();
						break;
					case 2:
						System.out.println("Option 2 selected");
						updatePassword(validatedUser);
						/* Test to see if the User[] was properly loaded */
//						UserService.displayUserArray();
						break;
					case 3:
						System.out.println("Option 3 selected");
						updateName(validatedUser);
						/* Test to see if the User[] was properly loaded */
//						UserService.displayUserArray();
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
//		if (username != "\n") {
			validatedUser.setUsername(username);
//		}
	} //end of updateUserName method
	
	private static void updatePassword(User validatedUser) {
		System.out.println(UPDATE_PASSWORD_PROMPT);
		String password = scanner.nextLine();
//		if (password != null || password != "\n") {
			validatedUser.setPassword(password);
//		}
	} //end of updatePassword method
	
	private static void updateName(User validatedUser) {
		System.out.println(UPDATE_NAME_PROMPT);
		String name = scanner.nextLine();
//		if (name != null || name != "\n") {
			validatedUser.setName(name);
//		}
	} //end of updateName method

} //end of Assignment4App class (main program)
