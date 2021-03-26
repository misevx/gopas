package cz.gopas.review.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.gopas.review.bean.Review;
import cz.gopas.review.bean.ReviewDTO;
import cz.gopas.review.persistence.GenericStorage;

@RestController
@RequestMapping("/review")
public class ReviewService {

	@Autowired
	@Qualifier("nosql")
	private GenericStorage storage;
	
	
	private HttpHeaders prepareHeaders(String id) {
		HttpHeaders hdr = new HttpHeaders();
		hdr.set("invalid-review-id", id);
		return hdr;
	}
	
	@GetMapping
	public ResponseEntity<List<Review>> getAllReviews() {
		List<Review> data = storage.readAll();
		if(data.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(data, HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Review> getReview(@PathVariable String id) {
		Optional<Review> opt = storage.read(id);
		if(opt.isEmpty()) {
			return new ResponseEntity<>(null, prepareHeaders(id), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(opt.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Review> createNewReview(@RequestBody ReviewDTO reviewDto) {
		System.err.println("Called");
		return new ResponseEntity<>(storage.create(reviewDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public Review updateReview(@PathVariable String id, @RequestBody ReviewDTO reviewDto) {
		return storage.update(id, reviewDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteReview(@PathVariable String id) {
		if(storage.delete(id))
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		return new ResponseEntity<Boolean>(false, prepareHeaders(id), HttpStatus.NOT_FOUND);
	}
	
	
}