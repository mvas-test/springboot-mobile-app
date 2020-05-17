package com.appsdeveloper.app.ws.entity.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequest {

	@NotNull(message="First Name cannot be null")
	@Size(min=4, message="First Name must be at least 4 characters")
	private String firstName;
	
	@NotNull(message="Last Name cannot be null")
	private String lastName;
	
	@NotNull(message="Email cannot be null")
	@Email(message="Please provide a valid email address")
	private String email;
	
	@NotNull(message="Password cannot be null")
	@Size(min=8, max=16, message=("Password must be beytween 8 and 16 characters"))
	private String password;
	
	public UserRequest() {	
		//default constructor
	}

	public UserRequest(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserRequest [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + "]";
	}
		
}
