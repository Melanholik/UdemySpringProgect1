package by.melanholik.udemy.springProject.controller;

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

    @Autowired
    public PersonController(ObjectDAO<Person> personDao) {
        this.personDao = personDao;
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
