package cz.gopas.book.persistence;

import org.springframework.data.repository.CrudRepository;

import cz.gopas.book.bean.Book;

public interface BookRepository extends CrudRepository<Book,String> {
}
