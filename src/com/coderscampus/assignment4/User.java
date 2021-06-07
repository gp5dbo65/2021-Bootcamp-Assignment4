package com.coderscampus.assignment4;

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
		this.setUsername(null);
		this.setPassword(null);
		this.setName(null);
		this.setRole(null);
	} //No-argument User constructor (needed this for SuperUser class)
	
	public User (String username, String password, String name) {
		this.setUsername(username);
		this.setPassword(password);
		this.setName(name);
		this.setRole("normal_user"); //normal_user is default role
	} //3-argument User constructor
	
	public User (String[] userInfo) {
		this.setUsername(userInfo[0]);
		this.setPassword(userInfo[1]);
		this.setName(userInfo[2]);
		this.setRole(userInfo[3]);
	} //1-argument String[] User constructor
	
	/* Override Methods */
	/* Use this toString method if you plan to pass an index to display*/
	public String toString(int i) {
		return "User[" + i + "]: username=" + username + ", password=" + password +
				", name=" + name + ", role=" + role;
	}
	
	@Override
	public String toString() {
		return "User: username=" + username + ", password=" + password +
				", name=" + name + ", role=" + role;
	} //end of toString() method
	
	@Override
	public int compareTo(User that) {
		int compareValue = that.getRole().compareTo(this.getRole());
		if (compareValue == 0) {	// this & that User objects have the same role
			compareValue = that.getUsername().compareTo(this.getUsername()); // set compareValue by Username
			//TODO: May have to add logic to compare name of user if role & username are duplicates
		}
		return compareValue;
	} //end of compareTo method
	
	
} // end of User class
