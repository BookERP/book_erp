package main.java.com.bookstore.dao;

import java.util.List;

public interface Crud<T> {
    void create(T t);
    T read(String id);
    void update(T t);
    void delete(String id);
    List<T> readAll();
}
