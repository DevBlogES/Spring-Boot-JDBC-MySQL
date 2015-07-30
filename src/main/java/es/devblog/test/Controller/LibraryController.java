package es.devblog.test.Controller;

import es.devblog.test.Model.Book;
import es.devblog.test.Repository.LibraryRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/books")
public class LibraryController {

	@Autowired
	private LibraryRep libraryRepository;

	@RequestMapping(method = GET)
	public List<Book> findAll() {
		return libraryRepository.findAll();
	}

	@RequestMapping(value = "/{id}", method = GET)
	public Book findOne(@PathVariable Long id) {
		return libraryRepository.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		libraryRepository.delete(id);
	}
}
