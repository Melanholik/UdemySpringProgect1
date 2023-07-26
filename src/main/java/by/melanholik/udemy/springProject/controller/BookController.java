package by.melanholik.udemy.springProject.controller;


import by.melanholik.udemy.springProject.dao.ObjectDAO;
import by.melanholik.udemy.springProject.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final ObjectDAO<Book> bookDAO;

    @Autowired
    public BookController(ObjectDAO<Book> bookDAO) {
        this.bookDAO = bookDAO;
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
    public String add(Book book) {
        bookDAO.add(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String getByIdForEdit(@PathVariable int id, Model model) {
        Optional<Book> book = bookDAO.getById(id);
        model.addAttribute("book", book.get());
        return "/book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") Book book) {
        bookDAO.update(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable int id, Model model) {
        Optional<Book> book = bookDAO.getById(id);
        model.addAttribute("book", book.get());
        return "/book/book";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable int id) {
        bookDAO.deleteById(id);
        return "redirect:/books";
    }
}
