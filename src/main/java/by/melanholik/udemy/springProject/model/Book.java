package by.melanholik.udemy.springProject.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotBlank(message = "The 'name' field cannot be empty")
    @Size(min = 2, message = "The 'name' field must contain at least 2 characters")
    private String name;
    @NotBlank(message = "The 'author' field cannot be empty")
    @Size(min = 2, message = "The 'author' field must contain at least 2 characters")
    private String author;
    @NotNull(message = "The 'releaseYear' field cannot be empty")
    private int releaseYear;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
