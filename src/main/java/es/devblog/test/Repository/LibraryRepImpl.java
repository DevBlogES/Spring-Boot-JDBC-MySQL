package es.devblog.test.Repository;

import es.devblog.test.Model.Book;
import es.devblog.test.Model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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
			Genre.valueOf(rs.getString("genre"))
	);

	@Override
	public Book findOne(Long id) {
		Book book = jdbcTemplate.queryForObject("SELECT * FROM Books WHERE book_id=?", new Object[]{id}, bookRowMapper);

		return book;
	}

	@Override
	public Book findOne(String isbn) {
		try {
			Book book = jdbcTemplate.queryForObject("SELECT * FROM Books WHERE isbn=?", new String[]{isbn}, bookRowMapper);
			return book;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	@Override
	public List<Book> findAll() {
		List<Book> books = jdbcTemplate.query("SELECT * FROM Books", bookRowMapper);

		return books;
	}

	@Override
	public Book save(Book book) {

		jdbcTemplate.update(
				"INSERT INTO Books (isbn, author, title, num_sells, published_date, genre) VALUES(?, ?, ?, ?, ?, ?)",
				book.getIsbn(), book.getAuthor(), book.getTitle(), book.getNumSells(), book.getPublishedDate().toString(), book.getGenre().name());

		Book savedBook = jdbcTemplate.queryForObject("SELECT * FROM Books WHERE isbn=?", new Object[]{book.getIsbn()}, bookRowMapper);

		return savedBook;
	}

	@Override
	public Book update(Book book) {
		Timestamp publishedDate = Timestamp.valueOf(book.getPublishedDate().toString());

		jdbcTemplate.update(
				"UPDATE Books WHERE book_id=? SET isbn=?, author=?, title=?, num_sells=?, published_date=?, genre=?",
				book.getId(), book.getIsbn(), book.getAuthor(), book.getTitle(), book.getNumSells(), publishedDate, book.getGenre().name());

		Book updatedBook = jdbcTemplate.queryForObject("SELECT * FROM Books WHERE isbn=?", new Object[]{book.getIsbn()}, bookRowMapper);

		return updatedBook;
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM Books WHERE book_id=?", new Object[]{id});
	}
}
