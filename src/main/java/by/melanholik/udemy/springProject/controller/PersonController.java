package by.melanholik.udemy.springProject.controller;

import by.melanholik.udemy.springProject.dao.BookPersonDAO;
import by.melanholik.udemy.springProject.dao.ObjectDAO;
import by.melanholik.udemy.springProject.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final ObjectDAO<Person> personDao;
    private final BookPersonDAO bookPersonDAO;

    @Autowired
    public PersonController(ObjectDAO<Person> personDao, BookPersonDAO personDAO) {
        this.personDao = personDao;
        this.bookPersonDAO = personDAO;
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
    public String add(Person person) {
        personDao.add(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    private String getById(@PathVariable int id, Model model) {
        model.addAttribute("person", personDao.getById(id).get());
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
        model.addAttribute("person", personDao.getById(id).get());
        return "/person/edit";
    }

    @PatchMapping("/{id}")
    private String change(@ModelAttribute("person") Person person) {
        personDao.update(person);
        return "redirect:/people";
    }
}
