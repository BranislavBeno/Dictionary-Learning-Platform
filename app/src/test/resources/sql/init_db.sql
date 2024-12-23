-- Users
INSERT INTO users (id, username, password, role, grade) VALUES (1, 'jane', '{noop}jane', 'ROLE_ADMIN', 1);
INSERT INTO users (id, username, password, role, grade) VALUES (2, 'bob', '{noop}bob', 'ROLE_USER', 1);
-- Lessons
INSERT INTO lessons (id, number, grade, user_id) VALUES (1, 1, 5, 1);
INSERT INTO lessons (id, number, grade, user_id) VALUES (2, 2, 5, 1);
INSERT INTO lessons (id, number, grade, user_id) VALUES (3, 3, 5, 1);
INSERT INTO lessons (id, number, grade, user_id) VALUES (4, 1, 3, 2);
-- Words
INSERT INTO words (id, en, sk, lesson_id) VALUES (1, 'father', 'otec', 1);
INSERT INTO words (id, en, sk, lesson_id) VALUES (2, 'mother', 'matka', 1);
INSERT INTO words (id, en, sk, lesson_id) VALUES (3, 'brother', 'brat', 1);
INSERT INTO words (id, en, sk, lesson_id) VALUES (4, 'sister', 'sestra', 1);
INSERT INTO words (id, en, sk, lesson_id) VALUES (5, 'water', 'voda', 2);
INSERT INTO words (id, en, sk, lesson_id) VALUES (6, 'fire', 'oheň', 2);
INSERT INTO words (id, en, sk, lesson_id) VALUES (7, 'lion', 'lev', 3);
INSERT INTO words (id, en, sk, lesson_id) VALUES (8, 'mom', 'mama', 4);
INSERT INTO words (id, en, sk, lesson_id) VALUES (9, 'dad', 'tata', 4);
