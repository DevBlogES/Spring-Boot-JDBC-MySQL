package es.devblog.test.Repository;

import es.devblog.test.Model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LibraryRepImpl implements LibraryRep {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<Book> bookRowMapper = (rs, rowNum) -> new Book(
			rs.getLong("book_id"),
			rs.getString("isbn"),
			rs.getString("author"),
			rs.getString("title"),
			rs.getInt("num_sells"),
			rs.getTimestamp("published_date").toLocalDateTime().toLocalDate(),
			rs.getString("genre")
	);

	@Override
	public Book findOne(Long id) {
		Book book = jdbcTemplate.queryForObject("SELECT * FROM Books WHERE book_id=?", new Object[]{id}, bookRowMapper);

		return book;
	}

	@Override
	public List<Book> findAll() {
		List<Book> books = jdbcTemplate.query("SELECT * FROM Books", bookRowMapper);

		return books;
	}

	@Override
	public void save(Book book) {

		jdbcTemplate.update(
				"INSERT INTO Books (isbn, author, title, num_sells, published_date, genre) VALUES(?, ?, ?, ?, ?, ?)",
				book.getIsbn(), book.getAuthor(), book.getTitle(), book.getNumSells(), book.getPublishedDate().toString(), book.getGenre());

	}

	@Override
	public void update(Book book) {

		jdbcTemplate.update(
				"UPDATE Books WHERE book_id=? SET isbn=?, author=?, title=?, num_sells=?, published_date=?, genre=?",
				book.getId(), book.getIsbn(), book.getAuthor(), book.getTitle(), book.getNumSells(), book.getPublishedDate().toString(), book.getGenre());

		Book updatedBook = jdbcTemplate.queryForObject("SELECT * FROM Books WHERE isbn=?", new Object[]{book.getIsbn()}, bookRowMapper);

	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM Books WHERE book_id=?", new Object[]{id});
	}
}
