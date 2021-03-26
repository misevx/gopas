package cz.gopas.book.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cz.gopas.book.bean.Book;
import cz.gopas.book.bean.BookDTO;

@Service
@Qualifier("sql")
public class SQLStorage implements GenericStorage {
	@Autowired
	private BookRepository repository;
	
	@Override
	public Book create(BookDTO bookDto) {
		Book book = new Book(bookDto);
		book.setId(UUID.randomUUID().toString());
		repository.save(book);
		return book;
	}
	
	@Override
	public List<Book> readAll() {
		List<Book> list = new ArrayList<>();
		for(Book book: repository.findAll())
			list.add(book);
		return list;
	}
	
	@Override
	public Optional<Book> read(String id) {
		return repository.findById(id);
	}
	@Override
	public boolean delete(String id) {
		repository.deleteById(id);
		return true;
	}
	@Override
	public Book update(String id, BookDTO bookDto) {
		Optional<Book> opt = read(id);
		if(opt.isPresent()) {
			Book book = opt.get();
			book.setAuthor(bookDto.getAuthor()).setTitle(bookDto.getTitle());
			return book;
		}
		return create(bookDto);
	}
}
