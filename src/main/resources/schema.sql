USE `library`;

DROP TABLE IF EXISTS `Books`;

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