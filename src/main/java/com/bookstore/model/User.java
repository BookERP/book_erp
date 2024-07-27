package main.java.com.bookstore.model;



import java.time.LocalDateTime;


public class User {
    private String userId;
    private String username;
    private String password;
    // 홍엽이 phone, address 추가
    private String phone;
    private String address;
    private String email;
    private UserRole role;
    private LocalDateTime registrationDate;


    public User() {}

    public User(String userId, String username, String password, String email, UserRole role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.registrationDate = LocalDateTime.now();
    }

    public User(String userId, String username, String phone, 
    			String email, String address) {
    	this.userId = userId;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.address = address;
	}

	// Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }
    public LocalDateTime getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDateTime registrationDate) { this.registrationDate = registrationDate; }

	public String getPhone() { return phone; }
	public void setPhone(String phone) { this.phone = phone; }

	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }
    
}

