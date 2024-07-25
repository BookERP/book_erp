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


    // 추가적으로 필요한 필드나 메서드 정의
    private String bookTitle; // 구매한 책의 제목을 저장할 필드

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}