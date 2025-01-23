package fr.learn.introSpring3.repository;

import fr.learn.introSpring3.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Trouver les livres d'un auteur par ID
    List<Book> findByAuthorId(Long authorId);

    // Trouver les livres d'une catégorie par ID
    List<Book> findByCategories_Id(Long categoryId);
}
