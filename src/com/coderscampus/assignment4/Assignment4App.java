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
	public static User[] users = new User[20];
	public static Scanner scanner = new Scanner(System.in);
//	private UserService userService = new UserService();
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		UserService.loadUsersArray(users);
		/* Test to see if the User[] was properly loaded */
//		UserService.displayUserArray(users);
		
		int loginAttempts = 0;
		boolean validLogin = false;
		User validatedUser = null;
		
		while (validatedUser == null && loginAttempts < MAX_TRIES) {
			System.out.println(EMAIL_PROMPT);
			String username = scanner.nextLine();
			System.out.println(PASSWORD_PROMPT);
			String password = scanner.nextLine();
			validatedUser = UserService.validateUser(username, password);
			
			if (validatedUser != null) {
				System.out.println(WELCOME_MESSAGE + validatedUser.getName());
				validLogin = true;
			} else {
				loginAttempts++;
				if (loginAttempts >= MAX_TRIES) {
					System.out.println(LOCKOUT_MESSAGE);
					validLogin = false;
				} else {
					System.out.println(INVALID_PROMPT);
				} //end of inner else block
			} //end of outer else block
		} //end of while loop

		if (validatedUser != null) {
			System.out.println(validatedUser);
		} else {
			System.out.println("not able to log in");
		}
		

	} //end of main method

} //end of Assignment4App
