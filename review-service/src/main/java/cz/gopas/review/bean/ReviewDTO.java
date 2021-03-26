package cz.gopas.review.bean;

public class ReviewDTO {
	private String bookId;
	private String author;
	private int stars;
	
	public ReviewDTO() {
		this(null, null, 0);
	}
	
	public ReviewDTO(String bookId, String author, int stars) {
		setBookId(bookId);
		setAuthor(author);
		setStars(stars);
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}
	
}
