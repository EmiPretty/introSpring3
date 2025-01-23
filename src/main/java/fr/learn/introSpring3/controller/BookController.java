package fr.learn.introSpring3.controller;

import fr.learn.introSpring3.entity.Book;
import fr.learn.introSpring3.dto.BookDto;
import fr.learn.introSpring3.repository.BookRepository;
import fr.learn.introSpring3.repository.AuthorRepository;
import fr.learn.introSpring3.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private BookDto convertToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setContent(book.getContent());
        bookDto.setAuthor_id(book.getAuthor() != null ? book.getAuthor().getId() : null);
        bookDto.setCategory_ids(book.getCategories().stream()
                .map(category -> category.getId())
                .collect(Collectors.toSet()));
        return bookDto;
    }

    private Book convertToEntity(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setContent(bookDto.getContent());
        book.setAuthor(authorRepository.findById(bookDto.getAuthor_id()).orElse(null));
        book.setCategories(new HashSet<>(categoryRepository.findAllById(bookDto.getCategory_ids())));

        return book;
    }

    @PostMapping
    public BookDto createBook(@RequestBody BookDto bookDto) {
        Book book = convertToEntity(bookDto);
        Book savedBook = bookRepository.save(book);
        return convertToDto(savedBook);
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(this::convertToDto).orElse(null);
    }

    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        if (bookRepository.existsById(id)) {
            bookDto.setId(id); // Garder l'ID du livre existant
            Book updatedBook = convertToEntity(bookDto);
            Book savedBook = bookRepository.save(updatedBook);
            return convertToDto(savedBook);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }

    @GetMapping("/author/{authorId}")
    public List<BookDto> getBooksByAuthor(@PathVariable Long authorId) {
        return bookRepository.findByAuthorId(authorId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category/{categoryId}")
    public List<BookDto> getBooksByCategory(@PathVariable Long categoryId) {
        return bookRepository.findByCategories_Id(categoryId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
