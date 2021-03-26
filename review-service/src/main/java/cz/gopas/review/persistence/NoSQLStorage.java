package cz.gopas.review.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cz.gopas.review.bean.Review;
import cz.gopas.review.bean.ReviewDTO;

@Service
@Qualifier("nosql")
public class NoSQLStorage implements GenericStorage {
	@Autowired
	private ReviewRepository repository;
	
	@Override
	public Review create(ReviewDTO reviewDto) {
		Review review = new Review();
		review.setAuthor(reviewDto.getAuthor());
		review.setBookId(reviewDto.getBookId());
		review.setStars(reviewDto.getStars());
		repository.save(review);
		return review;
	}
	
	@Override
	public List<Review> readAll() {
		return repository.findAll();
	}
	
	@Override
	public Optional<Review> read(String id) {
		return repository.findById(id);
	}
		
	@Override
	public boolean delete(String id) {
		repository.deleteById(id);
		return true;
	}
	@Override
	public Review update(String id, ReviewDTO reviewDto) {
		Optional<Review> opt = repository.findById(id);
		if(opt.isPresent()) {
			Review review = opt.get();
			review.setAuthor(reviewDto.getAuthor()).setBookId(reviewDto.getBookId()).setStars(reviewDto.getStars());
			return repository.save(review);
		}
		return this.create(reviewDto);
	}
}
