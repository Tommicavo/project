package it.fides.project.models.dtos;

import java.time.LocalDate;

public class BookDto {
	
	private String title;
	private double price;
	private LocalDate publicationDate;
	private int copiesSold;
	private boolean isBestseller;
	private Long idAuthor;
	
	public BookDto() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}

	public int getCopiesSold() {
		return copiesSold;
	}

	public void setCopiesSold(int copiesSold) {
		this.copiesSold = copiesSold;
	}

	public boolean isBestseller() {
		return isBestseller;
	}

	public void setBestseller(boolean isBestseller) {
		this.isBestseller = isBestseller;
	}

	public Long getIdAuthor() {
		return idAuthor;
	}

	public void setIdAuthor(Long idAuthor) {
		this.idAuthor = idAuthor;
	}
}
