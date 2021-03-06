package me.max.models;

import java.sql.Connection;

import me.max.dao.UserDAO;
import me.max.dao.UserDAOImpl;
import me.max.util.ConnectionUtil;

public class User {
	// Vars to hold user information
	private int id;
	private int role;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;

	// Constructor for adding all fields from db
	public User(int id, int role, String username, String password, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.role = role;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	// Create a user object for use in the system
	public static User loginUser(String username) throws Exception {
		// Doing this here, as this method is the only time it's relevant
		UserDAO ud = new UserDAOImpl();

		try (Connection con = ConnectionUtil.getConnection()) {
			User result = ud.getUserByUsername(con, username);
			return result;
		}
	}

	// Getters/setters for potentially user-editable fields

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// Getters for read-only fields
	public int getId() {
		return id;
	}

	public int getRole() {
		return role;
	}

	public String getUsername() {
		return username;
	}

	// hash, equals, toString

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + role;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role != other.role)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", role=" + role + ", username=" + username + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
}
