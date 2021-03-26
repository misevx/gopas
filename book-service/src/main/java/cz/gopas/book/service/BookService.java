package cz.gopas.book.service;

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

import java.util.List;
import java.util.Optional;

import cz.gopas.book.bean.Book;
import cz.gopas.book.bean.BookDTO;
import cz.gopas.book.persistence.GenericStorage;

@RestController
@RequestMapping("/book")
public class BookService {

	@Autowired
	@Qualifier("sql")
	private GenericStorage storage;
	
	
	private HttpHeaders prepareHeaders(String id) {
		HttpHeaders hdr = new HttpHeaders();
		hdr.set("invalid-book-id", id);
		return hdr;
	}
	
	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> data = storage.readAll();
		if(data.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(data, HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBook(@PathVariable String id) {
		Optional<Book> opt = storage.read(id);
		if(opt.isEmpty()) {
			return new ResponseEntity<>(null, prepareHeaders(id), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(opt.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Book> createNewBook(@RequestBody BookDTO bookDto) {
		return new ResponseEntity<>(storage.create(bookDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public Book updateBook(@PathVariable String id, @RequestBody BookDTO bookDto) {
		return storage.update(id, bookDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteBook(@PathVariable String id) {
		if(storage.delete(id))
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		return new ResponseEntity<Boolean>(false, prepareHeaders(id), HttpStatus.NOT_FOUND);
	}
	
	
}
