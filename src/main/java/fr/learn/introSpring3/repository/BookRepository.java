package fr.learn.introSpring3.repository;

import fr.learn.introSpring3.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByCategories_Id(Long categoryId);
    List<Book> findByAuthor_Id(Long authorId);
}
