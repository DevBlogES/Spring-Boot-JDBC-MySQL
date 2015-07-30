CREATE DATABASE IF NOT EXISTS `library`
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
USE `library`;


CREATE TABLE IF NOT EXISTS `Books` (
  book_id        SERIAL,
  isbn           VARCHAR(255) UNIQUE NOT NULL,
  author         VARCHAR(255)        NOT NULL,
  title          VARCHAR(255) UNIQUE NOT NULL,
  num_sells      BIGINT,
  published_date DATE,
  genre          VARCHAR(255)        NOT NULL,
  PRIMARY KEY (`book_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci
  COMMENT = 'Contains information about books';


INSERT INTO books (isbn, author, title, num_sells, published_date, genre)
VALUES ("978-1617291203", "Craig Walls", "Spring In Action", 130000, "2014-10-30", "TEXT_BOOK");

INSERT INTO books (isbn, author, title, num_sells, published_date, genre) VALUES
  ("978-0134171456", "Bill Phillips, Chris Stewart, Brian Hardy, Kristin Marsicano",
   "Android Programming: The Big Nerd Ranch Guide", 15713, "2015-07-30", "TEXT_BOOK");

INSERT INTO books (isbn, author, title, num_sells, published_date, genre)
VALUES ("860-1404651403", "Adam Freeman", "Pro AngularJS", 253768, "2014-02-27", "TEXT_BOOK");