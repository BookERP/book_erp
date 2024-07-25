package main.java.com.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private String bookId;
    private String isbn;
    private String author;
    private String publisher;
    private String publishDate;
    private double price;
    private String category;
    private String current;

    // 생성자, getter/setter, toString(), equals(), hashCode() 메서드 추가
}