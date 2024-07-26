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
public class Purchase {
    private String purchaseId;
    private String bookId;
    private String customerId;
    private Date purchaseDate;

    private String bookTitle; // 구매한 책 제목 여따 박음 DB에는 따로 생성 안함.


}