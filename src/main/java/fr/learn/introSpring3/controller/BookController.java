package fr.learn.introSpring3.controller;

import fr.learn.introSpring3.dto.BookDto;
import fr.learn.introSpring3.entity.Book;
import fr.learn.introSpring3.entity.Author;
import fr.learn.introSpring3.entity.Category;
import fr.learn.introSpring3.repository.BookRepository;
import fr.learn.introSpring3.repository.AuthorRepository;
import fr.learn.introSpring3.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public Book createBook(@RequestBody BookDto bookDto) {
        // Récupérer l'auteur et les catégories en fonction de leurs IDs
        Author author = authorRepository.findById(bookDto.getAuthor_id())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(bookDto.getCategory_ids()));

        // Créer un nouveau livre avec les données du DTO
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(author);
        book.setCategories(categories);

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
    public Book updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        Optional<Book> existingBookOpt = bookRepository.findById(id);
        if (existingBookOpt.isPresent()) {
            Book existingBook = existingBookOpt.get();

            Author author = authorRepository.findById(bookDto.getAuthor_id())
                    .orElseThrow(() -> new RuntimeException("Author not found"));

            Set<Category> categories = new HashSet<>(categoryRepository.findAllById(bookDto.getCategory_ids()));

            // Mettre à jour les champs du livre existant
            existingBook.setTitle(bookDto.getTitle());
            existingBook.setAuthor(author);
            existingBook.setCategories(categories);

            return bookRepository.save(existingBook);
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
