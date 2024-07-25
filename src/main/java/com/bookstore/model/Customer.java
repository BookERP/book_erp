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
public class Customer {
	private String customerId;
	private String name;
	private String phone;

	// 생성자, getter/setter, toString(), equals(), hashCode() 메서드 추가
}