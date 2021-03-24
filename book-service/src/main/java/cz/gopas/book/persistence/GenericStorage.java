package cz.gopas.book.persistence;

import java.util.List;
import java.util.Optional;

import cz.gopas.book.bean.Book;
import cz.gopas.book.bean.BookDTO;

public interface GenericStorage {
	public Book create(BookDTO bookDto);
	public List<Book> readAll();
	public Optional<Book> read(String id);
	public boolean delete(String id);
	public Book update(String id, BookDTO castleDto);
}
