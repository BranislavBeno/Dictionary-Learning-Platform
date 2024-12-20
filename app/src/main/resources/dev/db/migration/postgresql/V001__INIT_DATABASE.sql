CREATE SEQUENCE IF NOT EXISTS user_seq START WITH 10 INCREMENT BY 1;

CREATE TABLE users (
  id BIGINT NOT NULL,
   username VARCHAR(255),
   password VARCHAR(255),
   role     VARCHAR(255),
   grade    INTEGER NOT NULL,
   CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS word_seq START WITH 20 INCREMENT BY 1;

CREATE TABLE words (
  id BIGINT NOT NULL,
   en VARCHAR(255),
   sk VARCHAR(255),
   lesson INTEGER NOT NULL,
   grade INTEGER NOT NULL,
   user_id BIGINT,
   CONSTRAINT pk_word PRIMARY KEY (id)
);

ALTER TABLE words ADD CONSTRAINT FK_WORD_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);