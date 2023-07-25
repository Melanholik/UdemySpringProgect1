package by.melanholik.udemy.springProject.dao;

import java.util.List;
import java.util.Optional;

public interface ObjectDao<T> {

    List<T> get();

    Optional<T> getById(int id);

    void update(T t);

    void add(T t);

}
