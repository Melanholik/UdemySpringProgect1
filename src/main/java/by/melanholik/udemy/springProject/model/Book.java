package by.melanholik.udemy.springProject.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class Book {
    @NotBlank(message = "")
    @Size(min = 2, message = "")
    private String name;
    @NotBlank(message = "")
    @Size(min = 2, message = "")
    private String author;
    @NotNull
    private int releaseYear;

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
