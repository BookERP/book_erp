package main.java.com.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private String userId;
	private String account;
	private String pw;
	private String cpw;
	private String phone;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;
		return Objects.equals(userId, user.userId) && Objects.equals(account, user.account) && Objects.equals(pw, user.pw) && Objects.equals(cpw, user.cpw) && Objects.equals(phone, user.phone);
	}

	@Override
	public int hashCode() {
		int result = Objects.hashCode(userId);
		result = 31 * result + Objects.hashCode(account);
		result = 31 * result + Objects.hashCode(pw);
		result = 31 * result + Objects.hashCode(cpw);
		result = 31 * result + Objects.hashCode(phone);
		return result;
	}
}
