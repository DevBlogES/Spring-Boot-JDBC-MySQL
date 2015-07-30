package es.devblog.test.Repository;

import es.devblog.test.Model.Book;
import es.devblog.test.Model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class LibraryRepImpl implements LibraryRep {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Book findOne(Long id) {
		Book book = jdbcTemplate.queryForObject(
				"SELECT * FROM Books WHERE book_id=?", new Object[]{id},
				(rs, rowNum) -> new Book(
						rs.getLong("book_id"),
						rs.getString("isbn"),
						rs.getString("author"),
						rs.getString("title"),
						rs.getInt("num_sells"),
						new Date(rs.getTimestamp("published_date").getTime()),
						Genre.valueOf(rs.getString("genre"))
				)
		);

		return book;
	}

	@Override
	public List<Book> findAll() {
		List<Book> books = jdbcTemplate.query(
				"SELECT * FROM Books",
				(rs, rowNum) -> new Book(
						rs.getLong("book_id"),
						rs.getString("isbn"),
						rs.getString("author"),
						rs.getString("title"),
						rs.getInt("num_sells"),
						new Date(rs.getTimestamp("published_date").getTime()),
						Genre.valueOf(rs.getString("genre"))
				)
		);

		return books;
	}

	@Override
	public Book save(Book book) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String publishedDate = simpleDateFormat.format(book.getPublishedDate());

		jdbcTemplate.update(
				"INSERT INTO Books (isbn, author, title, num_sells, published_date, genre) VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[]{book.getIsbn(), book.getAuthor(), book.getTitle(), book.getNumSells(), publishedDate, book.getGenre().name()}
		);

		Book savedBook = jdbcTemplate.queryForObject(
				"SELECT * FROM Books WHERE isbn=?", new Object[]{book.getIsbn()},
				(rs, rowNum) -> new Book(
						rs.getLong("book_id"),
						rs.getString("isbn"),
						rs.getString("author"),
						rs.getString("title"),
						rs.getInt("num_sells"),
						new Date(rs.getTimestamp("published_date").getTime()),
						Genre.valueOf(rs.getString("genre"))
				)
		);

		return savedBook;
	}

	@Override
	public Book update(Book book) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String publishedDate = simpleDateFormat.format(book.getPublishedDate());

		jdbcTemplate.update(
				"UPDATE Books WHERE book_id=? SET isbn=?, author=?, title=?, num_sells=?, published_date=?, genre=?",
				new Object[]{book.getId(), book.getIsbn(), book.getAuthor(), book.getTitle(), book.getNumSells(), publishedDate, book.getGenre().name()}
		);

		Book updatedBook = jdbcTemplate.queryForObject(
				"SELECT * FROM Books WHERE isbn=?", new Object[]{book.getIsbn()},
				(rs, rowNum) -> new Book(
						rs.getLong("book_id"),
						rs.getString("isbn"),
						rs.getString("author"),
						rs.getString("title"),
						rs.getInt("num_sells"),
						new Date(rs.getTimestamp("published_date").getTime()),
						Genre.valueOf(rs.getString("genre"))
				)
		);

		return updatedBook;
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM Books WHERE book_id=?", new Object[]{id});
	}
}
