package com.coderscampus.assignment4.domain;

public class SuperUser extends User {

	public SuperUser (String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.role = "super_user";
	} //end of 3-argument SuperUser constructor
} //end of SuperUser class
