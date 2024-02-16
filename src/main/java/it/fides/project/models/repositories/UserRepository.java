package it.fides.project.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import it.fides.project.models.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findUserByEmailUser(String email);
	
    @Query("SELECT u FROM UserEntity u ORDER BY u.birthDateUser DESC LIMIT 2")
    List<UserEntity> getTwoYoungestUsers();
    
    @Query("SELECT u.idUser, COUNT(b.idBook) AS numBooks " +
            "FROM UserEntity u " +
            "LEFT JOIN u.books b " +
            "GROUP BY u.idUser " +
            "ORDER BY numBooks ASC LIMIT 3")
    List<Object[]> findUsersWithLeastBooks();
}
