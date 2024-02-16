package it.fides.project.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "author")
public class AuthorEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_author")
	private Long idAuthor;
	
	@Column(name = "first_name", nullable = false)
	private String firstNameAuthor;
	
	@Column(name = "last_name", nullable = false)
	private String lastNameAuthor;
	
	public AuthorEntity() {}

	public Long getIdAuthor() {
		return idAuthor;
	}

	public void setIdAuthor(Long idAuthor) {
		this.idAuthor = idAuthor;
	}

	public String getFirstNameAuthor() {
		return firstNameAuthor;
	}

	public void setFirstNameAuthor(String firstNameAuthor) {
		this.firstNameAuthor = firstNameAuthor;
	}

	public String getLastNameAuthor() {
		return lastNameAuthor;
	}

	public void setLastNameAuthor(String lastNameAuthor) {
		this.lastNameAuthor = lastNameAuthor;
	}
}
