package fr.learn.introSpring3.dto;

import java.util.Set;

public class PostDto {
    private Long id;
    private String title;
    private String content;
    private Long author_id;
    private Long category_id;
    private Set<Long> category_ids;
    private Set<Long> book_ids;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public Set<Long> getCategory_ids() {
        return category_ids;
    }

    public void setCategory_ids(Set<Long> category_ids) {
        this.category_ids = category_ids;
    }

    public Set<Long> getBook_ids() {
        return book_ids;
    }

    public void setBook_ids(Set<Long> book_ids) {
        this.book_ids = book_ids;
    }
}
