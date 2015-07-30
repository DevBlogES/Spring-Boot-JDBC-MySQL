package es.devblog.test.Model;

import java.util.Date;

public class Book {

	private Long id;
	private String isbn;
	private String author;
	private String title;
	private int numSells;
	private Date publishedDate;
	private Genre genre;

	public Book() {
	}

	public Book(Long id, String isbn, String author, String title, int numSells, Date publishedDate, Genre genre) {
		this.id = id;
		this.isbn = isbn;
		this.author = author;
		this.title = title;
		this.numSells = numSells;
		this.publishedDate = publishedDate;
		this.genre = genre;
	}

	public Long getId() {
		return id;
	}

	public String getIsbn() {
		return isbn;
	}

	public Book setIsbn(String isbn) {
		this.isbn = isbn;
		return this;
	}

	public String getAuthor() {
		return author;
	}

	public Book setAuthor(String author) {
		this.author = author;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Book setTitle(String title) {
		this.title = title;
		return this;
	}

	public int getNumSells() {
		return numSells;
	}

	public Book setNumSells(int numSells) {
		this.numSells = numSells;
		return this;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public Book setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
		return this;
	}

	public Genre getGenre() {
		return genre;
	}

	public Book setGenre(Genre genre) {
		this.genre = genre;
		return this;
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", isbn='" + isbn + '\'' +
				", author='" + author + '\'' +
				", title='" + title + '\'' +
				", numSells=" + numSells +
				", publishedDate=" + publishedDate +
				", genre=" + genre +
				'}';
	}
}
