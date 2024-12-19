-- User
INSERT INTO users (id, username, password, role, grade) VALUES (1, 'jane', '{noop}jane', 'ROLE_ADMIN', 1);
INSERT INTO users (id, username, password, role, grade) VALUES (2, 'bob', '{noop}bob', 'ROLE_USER', 1);
-- Word
INSERT INTO words (id, en, sk, lesson, grade, user_id) VALUES (1, 'father', 'otec', 1, 1, 1);
INSERT INTO words (id, en, sk, lesson, grade, user_id) VALUES (2, 'mother', 'matka', 1, 1, 1);
INSERT INTO words (id, en, sk, lesson, grade, user_id) VALUES (3, 'brother', 'brat', 1, 1, 1);
INSERT INTO words (id, en, sk, lesson, grade, user_id) VALUES (4, 'sister', 'sestra', 1, 1, 1);
INSERT INTO words (id, en, sk, lesson, grade, user_id) VALUES (5, 'water', 'voda', 1, 2, 1);
INSERT INTO words (id, en, sk, lesson, grade, user_id) VALUES (6, 'fire', 'oheň', 1, 2, 1);
INSERT INTO words (id, en, sk, lesson, grade, user_id) VALUES (7, 'lion', 'lev', 2, 2, 1);
INSERT INTO words (id, en, sk, lesson, grade, user_id) VALUES (8, 'mom', 'mama', 1, 1, 2);
INSERT INTO words (id, en, sk, lesson, grade, user_id) VALUES (9, 'dad', 'tata', 1, 1, 2);
