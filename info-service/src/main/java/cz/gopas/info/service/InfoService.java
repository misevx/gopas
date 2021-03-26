package cz.gopas.info.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cz.gopas.info.bean.Book;
import cz.gopas.info.bean.BookAggregate;
import cz.gopas.info.bean.Review;

@RestController
public class InfoService {

	@Autowired
	private RestTemplate template;
	
	@Value("${services.book.url}")
	private String bookUrl;
	
	@Value("${services.review.url}")
	private String reviewUrl;
	
	@GetMapping("/info/{bookId}")
	public ResponseEntity<BookAggregate> getBookInfo(@PathVariable(required=true) String bookId) {
		
		try {
			ResponseEntity<Book> resp = template.getForEntity(bookUrl + "/" + bookId, Book.class);
			
			if(resp.getStatusCode() != HttpStatus.OK)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			
			BookAggregate aggregate = new BookAggregate(resp.getBody());
			
			ResponseEntity<Review[]> respReview = template.getForEntity(reviewUrl + "?book=" + aggregate.getId(), Review[].class);
			
			if(respReview.getStatusCode() != HttpStatus.OK)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			
			aggregate.setReviews(Arrays.asList(respReview.getBody()));
			
			return new ResponseEntity<>(aggregate, HttpStatus.OK);	
			
		} catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
	}
}
