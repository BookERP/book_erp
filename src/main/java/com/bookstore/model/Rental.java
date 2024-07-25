package main.java.com.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rental {
    private String rentalId;
    private String bookId;
    private String customerId;
    private Date rentalDate;
    private Date returnDate;

    // 생성자, getter/setter, toString() 등의 메서드 추가


    // 대여한 책의 제목을 저장할 필드
    private String bookTitle;
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}