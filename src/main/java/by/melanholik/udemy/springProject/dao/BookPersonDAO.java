package by.melanholik.udemy.springProject.dao;

import by.melanholik.udemy.springProject.model.Book;
import by.melanholik.udemy.springProject.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookPersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookPersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(int bookId, int personId) {
        jdbcTemplate.update("INSERT INTO book_person(book_id, person_id) VALUES(?, ?)", bookId, personId);
    }

    public void delete(int bookId) {
        jdbcTemplate.update("DELETE FROM book_person WHERE book_id = ?", bookId);
    }

    public List<Book> getListBooksByPersonId(int personId) {
        return jdbcTemplate.query("SELECT id, name, author, release_year FROM book_person\n" +
                        "    LEFT JOIN book b on book_person.book_id = b.id\n" +
                        "WHERE person_id = ?;",
                new Object[]{personId}, new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Person> getPersonByBookId(int bookId) {
        return jdbcTemplate.query("SELECT id, name, birthday_year FROM book_person\n" +
                "    LEFT JOIN person p ON book_person.person_id = p.id\n" +
                "WHERE book_id = ?", new Object[]{bookId}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

}
