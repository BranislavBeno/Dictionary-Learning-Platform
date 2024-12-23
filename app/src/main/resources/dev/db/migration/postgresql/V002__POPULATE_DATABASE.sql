-- Users
INSERT INTO users (id, username, password, role, grade) VALUES (1, 'jane', '{noop}jane', 'ROLE_ADMIN', 5);
INSERT INTO users (id, username, password, role, grade) VALUES (2, 'bob', '{noop}bob', 'ROLE_USER', 3);
-- Lessons
INSERT INTO lessons (id, number, grade, user_id) VALUES (1, 1, 5, 1);
INSERT INTO lessons (id, number, grade, user_id) VALUES (2, 2, 5, 1);
INSERT INTO lessons (id, number, grade, user_id) VALUES (3, 3, 5, 1);
INSERT INTO lessons (id, number, grade, user_id) VALUES (4, 1, 3, 2);
-- Words
INSERT INTO words (id, en, sk, lesson_id) VALUES (1, 'mother', 'matka', 1);
INSERT INTO words (id, en, sk, lesson_id) VALUES (2, 'father', 'otec', 1);
INSERT INTO words (id, en, sk, lesson_id) VALUES (3, 'sister', 'sestra', 1);
INSERT INTO words (id, en, sk, lesson_id) VALUES (4, 'brother', 'brat', 1);
INSERT INTO words (id, en, sk, lesson_id) VALUES (5, 'grand mother', 'stará mama', 1);
INSERT INTO words (id, en, sk, lesson_id) VALUES (6, 'grand father', 'starý otec', 1);
INSERT INTO words (id, en, sk, lesson_id) VALUES (7, 'uncle', 'strýko', 1);
INSERT INTO words (id, en, sk, lesson_id) VALUES (8, 'aunt', 'teta', 1);
INSERT INTO words (id, en, sk, lesson_id) VALUES (9, 'water', 'voda', 2);
INSERT INTO words (id, en, sk, lesson_id) VALUES (10, 'fire', 'oheň', 2);
INSERT INTO words (id, en, sk, lesson_id) VALUES (11, 'lion', 'lev', 3);
INSERT INTO words (id, en, sk, lesson_id) VALUES (12, 'mom', 'mama', 4);
INSERT INTO words (id, en, sk, lesson_id) VALUES (13, 'dad', 'tata', 4);
INSERT INTO words (id, en, sk, lesson_id) VALUES (14, 'grandma', 'babka', 4);
INSERT INTO words (id, en, sk, lesson_id) VALUES (15, 'grandpa', 'dedko', 4);
