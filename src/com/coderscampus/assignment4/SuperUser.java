package com.coderscampus.assignment4;

public class SuperUser extends User {

	public SuperUser (String username, String password, String name) {
		this.setUsername(username);
		this.setPassword(password);
		this.setName(name);
		this.setRole("super_user");
	}
} //end of SuperUser class
