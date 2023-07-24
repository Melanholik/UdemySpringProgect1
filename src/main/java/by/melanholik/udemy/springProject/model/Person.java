package by.melanholik.udemy.springProject.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class Person {
    @NotBlank(message = "Name could be not NUll")
    private String name;
    @NotNull(message = "People must have birthday")
    private LocalDate birthday;

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
