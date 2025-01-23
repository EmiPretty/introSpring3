package fr.learn.introSpring3.service;

import fr.learn.introSpring3.entity.Book;
import fr.learn.introSpring3.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findByCategory(Long categoryId) {
        return bookRepository.findByCategories_Id(categoryId);
    }

    public List<Book> findByAuthor(Long authorId) {
        return bookRepository.findByAuthor_Id(authorId);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
