-- Users
INSERT INTO users (id, username, password, role, grade) VALUES (1, 'jane', '{noop}jane', 'ROLE_ADMIN', 1);
INSERT INTO users (id, username, password, role, grade) VALUES (2, 'bob', '{noop}bob', 'ROLE_USER', 1);
-- Lessons
INSERT INTO lessons (id, title, grade, success_rate, user_id) VALUES (1, 'Family', 5, NULL, 1);
INSERT INTO lessons (id, title, grade, success_rate, user_id) VALUES (2, 'Elements', 5, NULL, 1);
INSERT INTO lessons (id, title, grade, success_rate, user_id) VALUES (3, 'Animals', 5, NULL, 1);
INSERT INTO lessons (id, title, grade, success_rate, user_id) VALUES (4, 'Family', 3, NULL, 2);
-- Words
INSERT INTO words (id, en, sk, success_rate, lesson_id) VALUES (1, 'mother', 'matka', NULL, 1);
INSERT INTO words (id, en, sk, success_rate, lesson_id) VALUES (2, 'father', 'otec', NULL, 1);
INSERT INTO words (id, en, sk, success_rate, lesson_id) VALUES (3, 'sister', 'sestra', NULL, 1);
INSERT INTO words (id, en, sk, success_rate, lesson_id) VALUES (4, 'brother', 'brat', NULL, 1);
INSERT INTO words (id, en, sk, success_rate, lesson_id) VALUES (5, 'water', 'voda', NULL, 2);
INSERT INTO words (id, en, sk, success_rate, lesson_id) VALUES (6, 'fire', 'oheň', NULL, 2);
INSERT INTO words (id, en, sk, success_rate, lesson_id) VALUES (7, 'lion', 'lev', NULL, 3);
INSERT INTO words (id, en, sk, success_rate, lesson_id) VALUES (8, 'mom', 'mama', NULL, 4);
INSERT INTO words (id, en, sk, success_rate, lesson_id) VALUES (9, 'dad', 'tata', NULL, 4);
