package es.devblog.test.Controller;

import es.devblog.test.Model.Book;
import es.devblog.test.Repository.LibraryRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

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

	@RequestMapping(method = POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody Book book) {
		libraryRepository.save(book);
	}

	@RequestMapping(method = PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody Book book) {
		libraryRepository.update(book);
	}
}
