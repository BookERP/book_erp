package src.main.java.com.bookstore.model;

public class Product {
	
	private String productId;
	private String supplierid;
	private String name;
	private String author;
	private String publisher;
	private double price;
	private int stuckQuantity;
	private String category;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getSupplierid() {
		return supplierid;
	}
	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStuckQuantity() {
		return stuckQuantity;
	}
	public void setStuckQuantity(int stuckQuantity) {
		this.stuckQuantity = stuckQuantity;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", supplierid=" + supplierid + ", name=" + name + ", author="
				+ author + ", publisher=" + publisher + ", price=" + price + ", stuckQuantity=" + stuckQuantity
				+ ", category=" + category + "]";
	}
}