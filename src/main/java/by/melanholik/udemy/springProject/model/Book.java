package by.melanholik.udemy.springProject.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class Book {
    @NotBlank(message = "У книги должно быть название")
    @Size(min = 2, message = "Длина названии книги должна быть длиннее 2х символов")
    private String name;
    @NotBlank(message = "У книги должен быть автор")
    @Size(min = 2, message = "Длина имени автора должна быть длиннее 2х символов")
    private String author;
    @NotNull
    private LocalDate releaseYear;

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

    public LocalDate getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(LocalDate releaseYear) {
        this.releaseYear = releaseYear;
    }
}
