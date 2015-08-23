package es.devblog.test.Service;

import es.devblog.test.Exception.LibraryException;
import es.devblog.test.Model.Book;
import es.devblog.test.Model.Genre;
import es.devblog.test.Repository.LibraryRep;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private LibraryRep libraryRepository;

	@Override
	public Book save(Book book) {
		if (libraryRepository.findOne(book.getIsbn()) != null) {
			throw new LibraryException("Book not saved into the database, already exist.");
		}

		if (EnumUtils.isValidEnum(Genre.class, book.getGenre().name())) {
			throw new LibraryException("Book " + book.getTitle() + " doesn't have a valid genre.");
		}

		return libraryRepository.save(book);
	}
}
