package cz.gopas.review.persistence;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import cz.gopas.review.bean.Review;

public interface ReviewRepository extends MongoRepository<Review,String> {
}