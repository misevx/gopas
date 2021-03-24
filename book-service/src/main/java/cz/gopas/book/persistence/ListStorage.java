package cz.gopas.book.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import cz.gopas.book.bean.Book;
import cz.gopas.book.bean.BookDTO;

@Component
@Qualifier("list")
public class ListStorage implements GenericStorage {
	
	@Autowired
	private List<Book> storage;
	
	public Book create(BookDTO bookDto) {
		Book book = new Book(bookDto.getTitle(), bookDto.getAuthor());
		book.setId(UUID.randomUUID().toString());
		storage.add(book);
		return book;
	}
	
	public List<Book> readAll() {
		return storage;
	}
	
	public Optional<Book> read(String id) {
		return storage.stream()
					  .filter(book -> book.getId().equals(id))
					  .findFirst();
	}
	
	public boolean delete(String id) {
		return storage.removeIf(book -> book.getId().equals(id));
	}
	
	public Book update(String id, BookDTO castleDto) {
		Optional<Book> opt = read(id);
		if(opt.isEmpty())
			return create(castleDto);
		return opt.get().setAuthor(castleDto.getAuthor()).setTitle(castleDto.getTitle());
	}
}
