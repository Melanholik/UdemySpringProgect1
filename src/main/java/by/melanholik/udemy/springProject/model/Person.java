package by.melanholik.udemy.springProject.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Person {

    private int id;
    @NotBlank(message = "Name could be not NUll")
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
