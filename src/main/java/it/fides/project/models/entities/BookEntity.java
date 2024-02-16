package it.fides.project.models.entities;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class BookEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_book")
	private Long idBook;
	
	@Column(name = "title", nullable = false, unique = true)
	private String titleBook;
	
	@Column(name = "price")
	private double priceBook;
	
	@Column(name = "publication_date")
	private LocalDate publicationDateBook;
	
	@Column(name = "copies_sold")
	private int copiesSoldBook;
	
	@Column(name = "is_bestseller")
	private boolean isBestsellerBook;
	
	@ManyToOne
	@JoinColumn(name = "id_author")
	private AuthorEntity author;
	
	@ManyToMany
	@JsonBackReference
    @JoinTable(
            name = "user_book",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<UserEntity> users;
	
	public BookEntity() {}

	public Long getIdBook() {
		return idBook;
	}

	public void setIdBook(Long idBook) {
		this.idBook = idBook;
	}

	public String getTitleBook() {
		return titleBook;
	}

	public void setTitleBook(String titleBook) {
		this.titleBook = titleBook;
	}

	public double getPriceBook() {
		return priceBook;
	}

	public void setPriceBook(double priceBook) {
		this.priceBook = priceBook;
	}

	public LocalDate getPublicationDateBook() {
		return publicationDateBook;
	}

	public void setPublicationDateBook(LocalDate publicationDateBook) {
		this.publicationDateBook = publicationDateBook;
	}

	public int getCopiesSoldBook() {
		return copiesSoldBook;
	}

	public void setCopiesSoldBook(int copiesSoldBook) {
		this.copiesSoldBook = copiesSoldBook;
	}

	public boolean isBestsellerBook() {
		return isBestsellerBook;
	}

	public void setBestsellerBook(boolean isBestsellerBook) {
		this.isBestsellerBook = isBestsellerBook;
	}

	public AuthorEntity getAuthor() {
		return author;
	}

	public void setAuthor(AuthorEntity author) {
		this.author = author;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
}
