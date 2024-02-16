package it.fides.project.models.dtos;

import java.time.LocalDate;

public class UserDto {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private LocalDate birthDate;
	private boolean isYoung;
	private Long idRole;
	
	public UserDto() {}

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

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isYoung() {
		return isYoung;
	}

	public void setYoung(boolean isYoung) {
		this.isYoung = isYoung;
	}

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}
}
