package es.devblog.test.Repository;

import es.devblog.test.Model.Book;

import java.util.List;

/**
 * Created by Warren on 29/07/2015.
 */
public interface LibraryRep {
	Book findOne(Long id);

	List<Book> findAll();

	void save(Book book);

	void update(Book book);

	void delete(Long id);
}
