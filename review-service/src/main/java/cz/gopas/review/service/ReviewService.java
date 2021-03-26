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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cz.gopas.review.bean.Review;
import cz.gopas.review.bean.ReviewDTO;
import cz.gopas.review.persistence.GenericStorage;

@RestController
public class ReviewService {

	@Autowired
	@Qualifier("nosql")
	private GenericStorage storage;
	
	
	private HttpHeaders prepareHeaders(String id) {
		HttpHeaders hdr = new HttpHeaders();
		hdr.set("invalid-review-id", id);
		return hdr;
	}
	
	@GetMapping("/reviewbybook")
	public ResponseEntity<List<Review>> getReviewsByBook(@RequestParam(required=true) String book, @RequestParam(required=false,defaultValue="1") int minstars) {
		
		if(minstars <= 0 || minstars > 10) {
			HttpHeaders hdrs = new HttpHeaders();
			hdrs.add("failure-reason", "Number of stars out of range");
			return new ResponseEntity<>(null, hdrs, HttpStatus.BAD_REQUEST);
		}
		
		List<Review> reviews = storage.readByBookBetterThan(book, minstars);
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}
	
	@GetMapping("/reviewall")
	public ResponseEntity<List<Review>> getAllReviews() {
		List<Review> data = storage.readAll();
		if(data.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(data, HttpStatus.OK);
		
	}
	
	@GetMapping("/review")
	public ResponseEntity<Review> getReview(@RequestParam(required=true) String id) {
		Optional<Review> opt = storage.read(id);
		if(opt.isEmpty()) {
			return new ResponseEntity<>(null, prepareHeaders(id), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(opt.get(), HttpStatus.OK);
	}
	
	@PostMapping("/review")
	public ResponseEntity<Review> createNewReview(@RequestBody ReviewDTO reviewDto) {
		System.err.println("Called");
		return new ResponseEntity<>(storage.create(reviewDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/review")
	public Review updateReview(@RequestParam(required=true) String id, @RequestBody ReviewDTO reviewDto) {
		return storage.update(id, reviewDto);
	}
	
	@DeleteMapping("/review")
	public ResponseEntity<Boolean> deleteReview(@RequestParam(required=true) String id) {
		if(storage.delete(id))
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		return new ResponseEntity<Boolean>(false, prepareHeaders(id), HttpStatus.NOT_FOUND);
	}
	
	
}