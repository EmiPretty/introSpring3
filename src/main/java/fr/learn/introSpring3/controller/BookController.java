package fr.learn.introSpring3.controller;

import fr.learn.introSpring3.entity.Book;
import fr.learn.introSpring3.entity.Author;
import fr.learn.introSpring3.entity.Category;
import fr.learn.introSpring3.repository.BookRepository;
import fr.learn.introSpring3.repository.AuthorRepository;
import fr.learn.introSpring3.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        if (bookRepository.existsById(id)) {
            book.setId(id); // Garder l'ID du livre existant
            return bookRepository.save(book);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }

    @GetMapping("/author/{authorId}")
    public List<Book> getBooksByAuthor(@PathVariable Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    @GetMapping("/category/{categoryId}")
    public List<Book> getBooksByCategory(@PathVariable Long categoryId) {
        return bookRepository.findByCategories_Id(categoryId);
    }
}
