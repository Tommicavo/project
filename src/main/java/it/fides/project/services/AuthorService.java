package it.fides.project.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.fides.project.models.dtos.AuthorDto;
import it.fides.project.models.entities.AuthorEntity;
import it.fides.project.models.repositories.AuthorRepository;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorRepository authorRepo;
	
	public List<AuthorEntity> getAllAuthors() {
		return authorRepo.findAll();
	}
	
	public AuthorEntity getAuthor(Long id) {
		return authorRepo.findById(id).get();
	}
	
	public AuthorEntity insertAuthor(AuthorDto authorDto) {
		AuthorEntity author = new AuthorEntity();
		if (authorDto != null) {
			author.setFirstNameAuthor(authorDto.getFirstName());
			author.setLastNameAuthor(authorDto.getLastName());
		}
		return authorRepo.save(author);
	}
	
	public AuthorEntity updateAuthor(Long id, AuthorDto authorDto) {
		AuthorEntity author = getAuthor(id);
		AuthorEntity updatedAuthor = null;
		
		if (author != null) {
			author.setFirstNameAuthor(authorDto.getFirstName());
			author.setLastNameAuthor(authorDto.getLastName());
			
			updatedAuthor = authorRepo.save(author);
		}
		return updatedAuthor;
	}
	
	public void deleteAuthor(Long id) {
		authorRepo.deleteById(id);
	}
}
