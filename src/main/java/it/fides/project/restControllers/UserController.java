package it.fides.project.restControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.fides.project.models.dtos.PurchaseDto;
import it.fides.project.models.dtos.UserDto;
import it.fides.project.models.entities.UserEntity;
import it.fides.project.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<UserEntity> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public UserEntity getUser(@PathVariable Long id) {
		return userService.getUser(id);
	}
	
	@PutMapping("/{id}")
	public UserEntity updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
		return userService.updateUser(id, userDto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}

	@PostMapping("/{id}")
	public UserEntity purchaseBooks(@PathVariable Long id, @RequestBody PurchaseDto purchaseDto) {
		return userService.purchaseBooks(id, purchaseDto);
	}
	
	@GetMapping("/checkYoung")
	public List<UserEntity> checkYoungUsers() {
		List<UserEntity> users = userService.getTwoYoungestUsers();
		String startWithConsonantRegex = "(?i)[^aeiou].*";

		for (UserEntity user : users) {
			if (user.getFirstNameUser().matches(startWithConsonantRegex)) {
				return userService.setYoungUsers(users);
			}
		}		
		return users;
	}
	
	/*
	@GetMapping("/mostPurchasedBooks")
	public Set<Long> getMostPurchasedBooks() {
		List<UserEntity> users = userService.getAllUsers();
		Set<Long> idGiftBooks = new HashSet<>();
		
		for (UserEntity user : users) {
			if (user.getBooks().size() >= 3) {
				List<BookEntity> books = user.getBooks();
				for (BookEntity book : books) {
					if (book.getPriceBook() >= 50.0) {
						idGiftBooks.add(book.getIdBook());
					}
				}
			}
		}
		return idGiftBooks;
	}	
	*/
	
	/*
	@GetMapping("/withLeastBooks")
	public List<Long> getUsersWithLeastBooks() {
		List<Object[]> usersWithLeastBooks = userService.getUsersWithLeastBooks();
		List<Long> idUserList = new ArrayList<>();
		for (Object[] rs : usersWithLeastBooks) {
		    Long userId = (Long) rs[0];
		    idUserList.add(userId);
		}
		return idUserList;
	}
	*/
	
	@GetMapping("/donateBooks")
	public void donateBooks() {
		Set<Long> idBooksSet = userService.getMostPurchasedBooks();
		List<Long> idUsers = userService.getUsersWithLeastBooks();
		List<Long> idBooks = new ArrayList<>(idBooksSet);
		
		PurchaseDto p = new PurchaseDto();
		p.setIdsBookList(idBooks);
		
		for (Long id : idUsers) {
			userService.purchaseBooks(id, p);
		}
	}
}
