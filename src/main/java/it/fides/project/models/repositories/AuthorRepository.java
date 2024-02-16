package it.fides.project.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import it.fides.project.models.entities.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long>{

}
