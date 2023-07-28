package by.melanholik.udemy.springProject.controller;

import by.melanholik.udemy.springProject.dao.BookPersonDAO;
import by.melanholik.udemy.springProject.dao.ObjectDAO;
import by.melanholik.udemy.springProject.model.Person;
import by.melanholik.udemy.springProject.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final ObjectDAO<Person> personDao;
    private final BookPersonDAO bookPersonDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(ObjectDAO<Person> personDao, BookPersonDAO personDAO, PersonValidator personValidator) {
        this.personDao = personDao;
        this.bookPersonDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("people", personDao.get());
        return "/person/people";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("person") Person person) {
        return "/person/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/person/new";
        }
        personDao.add(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    private String getById(@PathVariable int id, Model model) {
        Optional<Person> person = personDao.getById(id);
        if (person.isEmpty()) {
            model.addAttribute("id", id);
            return "/person/notFound";
        }
        model.addAttribute("person", person.get());
        model.addAttribute("books", bookPersonDAO.getListBooksByPersonId(id));
        return "/person/person";
    }

    @DeleteMapping("/{id}")
    private String deleteById(@PathVariable int id) {
        personDao.deleteById(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    private String getByIdForEdit(@PathVariable int id, Model model) {
        Optional<Person> person = personDao.getById(id);
        if (person.isEmpty()) {
            model.addAttribute("id", id);
            return "/person/notFound";
        }
        model.addAttribute("person", person.get());
        return "/person/edit";
    }

    @PatchMapping("/{id}")
    private String change(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/person/edit";
        }
        personDao.update(person);
        return "redirect:/people";
    }
}
