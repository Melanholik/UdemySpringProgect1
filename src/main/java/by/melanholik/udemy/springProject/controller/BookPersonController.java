package by.melanholik.udemy.springProject.controller;

import by.melanholik.udemy.springProject.dao.BookPersonDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookPerson")
public class BookPersonController {
    private final BookPersonDAO bookPersonDAO;

    public BookPersonController(BookPersonDAO bookPersonDAO) {
        this.bookPersonDAO = bookPersonDAO;
    }

    @DeleteMapping("/book/{id}")
    public String deleteByBookId(@PathVariable("id") int id) {
        bookPersonDAO.delete(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}")
    public String add(@PathVariable("id") int bookId, @ModelAttribute("personId") Integer personId) {
        bookPersonDAO.create(bookId, personId);
        return "redirect:/books/" + bookId;
    }
}
