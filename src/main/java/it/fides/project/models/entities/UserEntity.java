package it.fides.project.models.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "user")
public class UserEntity implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long idUser;
	
	@Column(name = "first_name", nullable = false)
	private String firstNameUser;
	
	@Column(name = "last_name", nullable = false)
	private String lastNameUser;
	
	@Column(name = "email", nullable = false, unique = true)
	private String emailUser;
	
	@Column(name = "password", nullable = false)
	private String passwordUser;
	
	@Column(name = "birth_date")
	private LocalDate birthDateUser;
	
	@Column(name = "is_young")
	private boolean isYoungUser;
	
	@ManyToOne
	@JoinColumn(name = "id_role")
	private RoleEntity role;
	
	@ManyToMany
    @JoinTable(
            name = "user_book",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_book"))
	@JsonManagedReference
    private List<BookEntity> books;
	
	public UserEntity() {}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getFirstNameUser() {
		return firstNameUser;
	}

	public void setFirstNameUser(String firstNameUser) {
		this.firstNameUser = firstNameUser;
	}

	public String getLastNameUser() {
		return lastNameUser;
	}

	public void setLastNameUser(String lastNameUser) {
		this.lastNameUser = lastNameUser;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getPasswordUser() {
		return passwordUser;
	}

	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}

	public LocalDate getBirthDateUser() {
		return birthDateUser;
	}

	public void setBirthDateUser(LocalDate birthDateUser) {
		this.birthDateUser = birthDateUser;
	}

	public boolean isYoungUser() {
		return isYoungUser;
	}

	public void setYoungUser(boolean isYoungUser) {
		this.isYoungUser = isYoungUser;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public List<BookEntity> getBooks() {
		return books;
	}

	public void setBooks(List<BookEntity> books) {
		this.books = books;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		if (getRole() != null) {
			authorities.add(new SimpleGrantedAuthority(getRole().getLabelRole()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return getPasswordUser();
	}

	@Override
	public String getUsername() {
		return getEmailUser();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
