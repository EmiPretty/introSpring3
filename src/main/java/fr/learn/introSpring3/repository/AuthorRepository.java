package fr.learn.introSpring3.repository;

import fr.learn.introSpring3.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {}
