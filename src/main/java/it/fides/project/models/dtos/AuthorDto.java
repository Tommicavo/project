package it.fides.project.models.dtos;

public class AuthorDto {

	private String firstName;
	private String lastName;
	
	public AuthorDto() {}

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
}
