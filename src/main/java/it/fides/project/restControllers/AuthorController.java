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
import it.fides.project.models.dtos.AuthorDto;
import it.fides.project.models.entities.AuthorEntity;
import it.fides.project.services.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	@GetMapping
	public List<AuthorEntity> getAllAuthors() {
		return authorService.getAllAuthors();
	}
	
	@GetMapping("/{id}")
	public AuthorEntity getAuthor(@PathVariable Long id) {
		return authorService.getAuthor(id);
	}
	
	@PostMapping
	public AuthorEntity insertAuthor(@RequestBody AuthorDto authorDto) {
		return authorService.insertAuthor(authorDto);
	}
	
	@PutMapping("/{id}")
	public AuthorEntity updateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
		return authorService.updateAuthor(id, authorDto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAuthor(@PathVariable Long id) {
		authorService.deleteAuthor(id);
	}
}
