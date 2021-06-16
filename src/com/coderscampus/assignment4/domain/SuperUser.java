package com.coderscampus.assignment4.domain;

public class SuperUser extends User {

	public SuperUser (String username, String password, String name) {
//		super();
//		this.username = username;
//		this.password = password;
//		this.name = name;
//		this.role = "super_user";
		
		this.setUsername(username);
		this.setPassword(password);
		this.setName(name);
		this.setRole("super_user");
	} //end of 3-argument SuperUser constructor
} //end of SuperUser class
