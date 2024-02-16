package it.fides.project.models.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import it.fides.project.models.entities.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>{
    @Query("SELECT b FROM BookEntity b " +
            "JOIN b.author a " +
            "ORDER BY a.lastNameAuthor, b.priceBook DESC")
    List<BookEntity> getBooksByAuthorAndPrice();
    
    @Query("SELECT b FROM BookEntity b " +
            "WHERE b.author.idAuthor = :authorId " +
            "ORDER BY b.publicationDateBook ASC")
     List<BookEntity> getBooksByAuthorAndPublication(Long authorId);
}
