package com.coderscampus.assignment4.domain;

public class User implements Comparable<User>{
	private String username;
	private String password;
	private String name;
	private String role;
	
	/* Getter and Setter Methods */
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	/* Constructors */
	public User() {
	} //No-argument User constructor (needed this for SuperUser class)
	
	public User (String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.role = "normal_user"; //normal_user is default role
	} //3-argument User constructor
	
	public User (String[] userInfo) {
		this.username = userInfo[0];
		this.password = userInfo[1];
		this.name = userInfo[2];
		this.role = userInfo[3];
	} //1-argument String[] User constructor
	
	/* This method is used to format the output string for the users.txt file */
	public String writeOutput() {
		return username + ", " + password + ", " +
				name + ", " + role + "\n";
	} //end of writeOutput method

	/* Use this toString method if you plan to pass an index to display */
	public String toString(int i) {
		return "User[" + i + "]: username=" + username + ", password=" + password +
				", name=" + name + ", role=" + role;
	} //end of toString(int i) method

	/* Override Methods */
	@Override
	public String toString() {
		return "User: username=" + username + ", password=" + password +
				", name=" + name + ", role=" + role;
	} //end of toString() method
	
	@Override
	public int compareTo(User that) {
		// sort roles in descending order (super before normal)
		int compareValue = that.getRole().compareTo(this.getRole());
		// this & that User objects have the same role
		if (compareValue == 0) {
			// sort username (email) in ascending order
			compareValue = this.getUsername().compareTo(that.getUsername());
		}
		return compareValue;
	} //end of compareTo method
	
} // end of User class
