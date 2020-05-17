package com.appsdeveloper.app.ws.entity.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserRequest {

	@NotNull(message="First Name cannot be null")
	@Size(min=4, message="First Name must be at least 4 characters")
	private String firstName;
	
	@NotNull(message="Last Name cannot be null")
	private String lastName;
	
	public UpdateUserRequest() {	
		//default constructor
	}

	public UpdateUserRequest(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
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

	

	@Override
	public String toString() {
		return "UserRequest [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
		
}
