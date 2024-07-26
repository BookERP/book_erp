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

    private String bookTitle;// 대여한 책 따로 저장할 변수. DB에는 따로 생성 안함.

}