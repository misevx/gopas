package cz.gopas.review.persistence;

import java.util.List;
import java.util.Optional;

import cz.gopas.review.bean.Review;
import cz.gopas.review.bean.ReviewDTO;

public interface GenericStorage {
	public Review create(ReviewDTO reviewDto);
	public List<Review> readAll();
	public Optional<Review> read(String id);
	public boolean delete(String id);
	public Review update(String id, ReviewDTO castleDto);
}
