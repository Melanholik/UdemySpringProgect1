package by.melanholik.udemy.springProject.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotBlank(message = "Поле 'name' не должно быть пустым")
    @Size(min = 2, message = "Поле 'name' должно содержать не менее 2 символов")
    private String name;
    @NotBlank(message = "Поле 'author' не должно быть пустым")
    @Size(min = 2, message = "Поле 'author' должно содержать не менее 2 символов")
    private String author;
    @NotNull(message = "Поле 'releaseYear' не должно быть пустым")
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
