package by.melanholik.udemy.springProject.controller;


import by.melanholik.udemy.springProject.dao.BookPersonDAO;
import by.melanholik.udemy.springProject.dao.ObjectDAO;
import by.melanholik.udemy.springProject.model.Book;
import by.melanholik.udemy.springProject.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final ObjectDAO<Book> bookDAO;
    private final BookPersonDAO bookPersonDAO;
    private final ObjectDAO<Person> personDAO;

    @Autowired
    public BookController(ObjectDAO<Book> bookDAO, BookPersonDAO bookPersonDAO, ObjectDAO<Person> personDAO) {
        this.bookDAO = bookDAO;
        this.bookPersonDAO = bookPersonDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String all(Model model) {
        model.addAttribute("books", bookDAO.get());
        return "/book/allBook";
    }

    @GetMapping("/new")
    public String creat(@ModelAttribute("book") Book book) {
        return "/book/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("book") @Valid Book book,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/book/new";
        }
        bookDAO.add(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String getByIdForEdit(@PathVariable int id, Model model) {
        Optional<Book> book = bookDAO.getById(id);
        if (book.isEmpty()) {
            model.addAttribute("id", id);
            return "/book/notFound";
        }
        model.addAttribute("book", book.get());
        return "/book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/book/edit";
        }
        bookDAO.update(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable int id, Model model) {
        Optional<Book> book = bookDAO.getById(id);
        if (book.isEmpty()) {
            model.addAttribute("id", id);
            return "/book/notFound";
        }
        model.addAttribute("book", book.get());
        Optional<Person> person = bookPersonDAO.getPersonByBookId(id);
        if (person.isPresent()) {
            model.addAttribute("isTaken", true);
            model.addAttribute("person", person.get());
        } else {
            model.addAttribute("isTaken", false);
            model.addAttribute("people", personDAO.get());
        }
        return "/book/book";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable int id) {
        bookDAO.deleteById(id);
        return "redirect:/books";
    }
}
