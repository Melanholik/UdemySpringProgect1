package by.melanholik.udemy.springProject.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Person {

    private int id;
    @NotBlank(message = "The 'name' field cannot be empty")
    @Size(min = 2, message = "The 'name' field must contain at least 2 characters")
    private String name;
    @NotNull(message = "People must have birthday")
    private int birthdayYear;

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

    public int getBirthdayYear() {
        return birthdayYear;
    }

    public void setBirthdayYear(int birthdayYear) {
        this.birthdayYear = birthdayYear;
    }
}
