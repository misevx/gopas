package cz.gopas.info.bean;

import java.util.List;

public class BookAggregate {
	private String id;
	private String title;
	private String author;
	private List<Review> reviews;
	
	public BookAggregate() {
		this(null,null,null);
	}
	
	public BookAggregate(Book book) {
		this(book.getId(), book.getTitle(), book.getAuthor());
	}
	
	public BookAggregate(String id, String title, String author) {
		setId(id).setTitle(title).setAuthor(author);
	}
	
	public BookAggregate setId(String id) {
		this.id = id;
		return this;
	}
	
	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	public BookAggregate setTitle(String title) {
		this.title = title;
		return this;
	}
	public String getAuthor() {
		return author;
	}
	public BookAggregate setAuthor(String author) {
		this.author = author;
		return this;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public BookAggregate setReviews(List<Review> reviews) {
		this.reviews = reviews;
		return this;
	}
		
}
