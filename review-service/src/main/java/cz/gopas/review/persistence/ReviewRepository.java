package cz.gopas.review.persistence;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import cz.gopas.review.bean.Review;

public interface ReviewRepository extends MongoRepository<Review,String> {
	
	@Query("{'bookId': ?0}")
	public List<Review> findByBook(String bookId);
	
	@Query("{'bookId': ?0, 'stars': {'$gt': ?1}}")
	public List<Review> findByBookBetterThan(String bookId, int minStars);
}