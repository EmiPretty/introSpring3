package fr.learn.introSpring3.controller;

import fr.learn.introSpring3.entity.Author;
import fr.learn.introSpring3.service.AuthorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }
}
