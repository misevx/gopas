package cz.gopas.review.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import cz.gopas.review.bean.Review;
import cz.gopas.review.bean.ReviewDTO;

@Component
@Qualifier("list")
public class ListStorage implements GenericStorage {
	
	@Autowired
	private List<Review> storage;
	
	public Review create(ReviewDTO reviewDto) {
		Review review = new Review(reviewDto);
		review.setId(UUID.randomUUID().toString());
		storage.add(review);
		return review;
	}
	
	public List<Review> readAll() {
		return storage;
	}
	
	@Override
	public List<Review> readByBook(String bookId) {
		return storage.stream()
				      .filter(review -> review.getBookId().equals(bookId))
				      .collect(Collectors.toList());
	}
	
	@Override
	public List<Review> readByBookBetterThan(String bookId, int minStars) {
		return storage.stream()
				      .filter(review -> review.getBookId().equals(bookId))
				      .filter(review -> review.getStars() > minStars)
				      .collect(Collectors.toList());		      
	}
	
	public Optional<Review> read(String id) {
		return storage.stream()
					  .filter(review -> review.getId().equals(id))
					  .findFirst();
	}
	
	public boolean delete(String id) {
		return storage.removeIf(review -> review.getId().equals(id));
	}
	
	public Review update(String id, ReviewDTO reviewDto) {
		Optional<Review> opt = read(id);
		if(opt.isEmpty())
			return create(reviewDto);
		return opt.get().setBookId(reviewDto.getBookId()).setAuthor(reviewDto.getAuthor()).setStars(reviewDto.getStars());
	}
}
