package it.fides.project.restControllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.fides.project.models.dtos.BookDto;
import it.fides.project.models.entities.BookEntity;
import it.fides.project.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public List<BookEntity> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	@GetMapping("/{id}")
	public BookEntity getBook(@PathVariable Long id) {
		return bookService.getBook(id);
	}
	
	@PostMapping
	public BookEntity insertBook(@RequestBody BookDto bookDto) {
		return bookService.insertBook(bookDto);
	}
	
	@PutMapping("/{id}")
	public BookEntity updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
		return bookService.updateBook(id, bookDto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
	}
	
	@GetMapping("/byAuthorAndPrice")
	public List<BookEntity> getBooksByAuthorAndPrice() {
		return bookService.getBooksByAuthorAndPrice();
	}
	
	@GetMapping("/byAuthorAndPublication/{id}")
	public List<BookEntity> getBooksByAuthorAndPublication(@PathVariable Long id) {
		return bookService.getBooksByAuthorAndPublication(id);
	}
}
