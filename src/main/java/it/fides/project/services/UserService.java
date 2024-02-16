package it.fides.project.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.fides.project.models.dtos.PurchaseDto;
import it.fides.project.models.dtos.UserDto;
import it.fides.project.models.entities.BookEntity;
import it.fides.project.models.entities.RoleEntity;
import it.fides.project.models.entities.UserEntity;
import it.fides.project.models.repositories.UserRepository;

@Service
public class UserService {
	
	private static final Long DEFAULT_ID_ROLE = 1L;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private BookService bookService;
	
	public List<UserEntity> getAllUsers() {
		return userRepo.findAll();
	}
	
	public UserEntity getUser(Long id) {
		return userRepo.findById(id).get();
	}
	
	public UserEntity getUserByEmail(String email) {
		return userRepo.findUserByEmailUser(email);
	}
	
	public UserEntity insertUser(UserDto userDto) {
		UserEntity user = new UserEntity();
		
		if (userDto != null) {
			user.setFirstNameUser(userDto.getFirstName());
			user.setLastNameUser(userDto.getLastName());
			user.setEmailUser(userDto.getEmail());
			user.setPasswordUser(userDto.getPassword());
			user.setBirthDateUser(userDto.getBirthDate());
			user.setYoungUser(false);
			user.setRole(roleService.getRole(DEFAULT_ID_ROLE));
		}
		return userRepo.save(user);
	}
	
	public UserEntity updateUser(Long id, UserDto userDto) {
		UserEntity user = getUser(id);
		UserEntity updatedUser = null;
		
		if (user != null) {
			user.setFirstNameUser(userDto.getFirstName());
			user.setLastNameUser(userDto.getLastName());
			user.setEmailUser(userDto.getEmail());
			user.setPasswordUser(userDto.getPassword());
			user.setBirthDateUser(userDto.getBirthDate());
			user.setYoungUser(userDto.isYoung());
			
			if (userDto.getIdRole() != null) {
				RoleEntity role = roleService.getRole(userDto.getIdRole());
				if (role != null) {
					user.setRole(role);
				}
			}
			updatedUser = userRepo.save(user);
		}
		return updatedUser;
	}
	
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}
	
	public UserEntity purchaseBooks(Long id, PurchaseDto purchaseDto) {
		UserEntity user = getUser(id);
		if (user != null) {
			List<BookEntity> books = new ArrayList<>();
			for (Long idBook : purchaseDto.getIdsBookList()) {
				BookEntity book = bookService.getBook(idBook);
				if (book != null) {
					bookService.updatePurchaseInfo(book);
					books.add(book);
				}
			}
			user.setBooks(books);
		}
		return userRepo.save(user);
	}
	
	public List<UserEntity> getTwoYoungestUsers() {
		return userRepo.getTwoYoungestUsers();
	}
	
	public List<UserEntity> setYoungUsers(List<UserEntity> users) {
		for (UserEntity user : users) {
			user.setYoungUser(true);
			userRepo.save(user);
		}
		return users;
	}
	
	public Set<Long> getMostPurchasedBooks() {
		List<UserEntity> users = getAllUsers();
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
	
	public List<Long> getUsersWithLeastBooks() {
		List<Object[]> usersWithLeastBooks = userRepo.findUsersWithLeastBooks();
		List<Long> idUserList = new ArrayList<>();
		for (Object[] rs : usersWithLeastBooks) {
		    Long userId = (Long) rs[0];
		    idUserList.add(userId);
		}
		return idUserList;
	}
}
