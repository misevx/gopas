package cz.gopas.book.bean;

public class Book {
	private String id;
	private String author;
	private String title;
	
	public Book() {
		this(null, null);
	}
	
	public Book(String title, String author) {
		setTitle(title);
		setAuthor(author);
	}
	
	public String getId() {
		return id;
	}
	public Book setId(String id) {
		this.id = id;
		return this;
	}
	public String getAuthor() {
		return author;
	}
	public Book setAuthor(String author) {
		this.author = author;
		return this;
	}
	public String getTitle() {
		return title;
	}
	public Book setTitle(String title) {
		this.title = title;
		return this;
	}
}
