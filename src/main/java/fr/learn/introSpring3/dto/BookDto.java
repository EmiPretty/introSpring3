package fr.learn.introSpring3.dto;

import java.util.Set;

public class BookDto {
    private Long id;
    private String title;
    private Long author_id;
    private Set<Long> category_ids;

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

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public Set<Long> getCategory_ids() {
        return category_ids;
    }

    public void setCategory_ids(Set<Long> category_ids) {
        this.category_ids = category_ids;
    }
}
