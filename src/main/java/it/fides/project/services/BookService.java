package it.fides.project.services;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.fides.project.models.dtos.BookDto;
import it.fides.project.models.entities.BookEntity;
import it.fides.project.models.repositories.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private AuthorService authorService;
	
	public List<BookEntity> getAllBooks() {
		return bookRepo.findAll();
	}
	
	public BookEntity getBook(Long id) {
		return bookRepo.findById(id).get();
	}
	
	public BookEntity insertBook(BookDto bookDto) {
		BookEntity book = new BookEntity();
		book.setTitleBook(bookDto.getTitle());
		book.setPriceBook(bookDto.getPrice());
		book.setPublicationDateBook(bookDto.getPublicationDate());
		book.setCopiesSoldBook(0);
		book.setBestsellerBook(false);
		book.setAuthor(authorService.getAuthor(bookDto.getIdAuthor()));
		return bookRepo.save(book);
	}
	
	public BookEntity updateBook(Long id, BookDto bookDto) {
		BookEntity book = getBook(id);
		
		if (book != null) {
			book.setTitleBook(bookDto.getTitle());
			book.setPriceBook(bookDto.getPrice());
			book.setPublicationDateBook(bookDto.getPublicationDate());
			book.setCopiesSoldBook(bookDto.getCopiesSold());
			book.setAuthor(authorService.getAuthor(bookDto.getIdAuthor()));
			return bookRepo.save(book);
		}
		return null;
	}
	
	public void deleteBook(Long id) {
		bookRepo.deleteById(id);
	}
	
	public List<BookEntity> getBooksByAuthorAndPrice() {
		return bookRepo.getBooksByAuthorAndPrice();
	}
	
	public BookEntity updatePurchaseInfo(BookEntity book) {
		if (book != null) {
			book.setCopiesSoldBook(book.getCopiesSoldBook() + 1);
			if (book.getCopiesSoldBook() >= 3) {
				book.setBestsellerBook(true);
			}
			if (book.isBestsellerBook() && book.getPublicationDateBook().isBefore(LocalDate.now().minusYears(10))) {
				book.setPriceBook(book.getPriceBook() * 0.8);
			}
			return bookRepo.save(book);
		}
		return null;
	}
	
	public List<BookEntity> getBooksByAuthorAndPublication(Long id) {
		return bookRepo.getBooksByAuthorAndPublication(id);
	}
}
