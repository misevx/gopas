package cz.gopas.review.bean;

public class Review {
	private String id;
	private String bookId;
	private String author;
	private int stars;
	
	public Review() {
		this(null, null, 0);
	}
	
	public Review(ReviewDTO reviewDto) {
		this(reviewDto.getBookId(), reviewDto.getAuthor(), reviewDto.getStars());
	}
	
	public Review(String bookId, String author, int stars) {
		setBookId(bookId);
		setAuthor(author);
		setStars(stars);
	}

	public String getId() {
		return id;
	}

	public Review setId(String id) {
		this.id = id;
		return this;
	}

	public String getBookId() {
		return bookId;
	}

	public Review setBookId(String bookId) {
		this.bookId = bookId;
		return this;
	}

	public String getAuthor() {
		return author;
	}

	public Review setAuthor(String author) {
		this.author = author;
		return this;
	}

	public int getStars() {
		return stars;
	}

	public Review setStars(int stars) {
		if(stars <= 0 )
			stars = 1;
		else if(stars > 10)
			stars = 10;
		this.stars = stars;
		
		return this;
	}
	
	
}
