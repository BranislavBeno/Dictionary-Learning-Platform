-- Users
CREATE SEQUENCE IF NOT EXISTS user_seq START WITH 10 INCREMENT BY 1;

CREATE TABLE users (
                       id BIGINT NOT NULL,
                       username VARCHAR(255),
                       password VARCHAR(255),
                       role     VARCHAR(255),
                       grade    INTEGER NOT NULL,
                       CONSTRAINT pk_users PRIMARY KEY (id)
);

-- Lessons
CREATE SEQUENCE IF NOT EXISTS lesson_seq START WITH 10 INCREMENT BY 1;

CREATE TABLE lessons (
                         id           BIGINT  NOT NULL,
                         title        VARCHAR(255),
                         grade        INTEGER NOT NULL,
                         success_rate DOUBLE PRECISION,
                         user_id BIGINT,
                         CONSTRAINT pk_lessons PRIMARY KEY (id)
);

ALTER TABLE lessons ADD CONSTRAINT FK_LESSONS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

-- Words
CREATE SEQUENCE IF NOT EXISTS word_seq START WITH 20 INCREMENT BY 1;

CREATE TABLE words (
                       id           BIGINT NOT NULL,
                       en           VARCHAR(255),
                       sk           VARCHAR(255),
                       success_rate DOUBLE PRECISION,
                       lesson_id    BIGINT,
                       CONSTRAINT pk_words PRIMARY KEY (id)
);

ALTER TABLE words ADD CONSTRAINT FK_WORDS_ON_LESSON FOREIGN KEY (lesson_id) REFERENCES lessons (id);
