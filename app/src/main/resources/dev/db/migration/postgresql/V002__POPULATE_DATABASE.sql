-- User
INSERT INTO users (id, username, password, role) VALUES (1, 'jane', '{noop}jane', 'admin');
INSERT INTO users (id, username, password, role) VALUES (2, 'bob', '{noop}bob', 'user');
-- Word
INSERT INTO words (id, en, sk, lesson, grade, user_id) VALUES (1, 'father', 'otec', 1, 5, 1);
INSERT INTO words (id, en, sk, lesson, grade, user_id) VALUES (2, 'mother', 'matka', 1, 5, 1);
INSERT INTO words (id, en, sk, lesson, grade, user_id) VALUES (3, 'brother', 'brat', 1, 5, 1);
INSERT INTO words (id, en, sk, lesson, grade, user_id) VALUES (4, 'sister', 'sestra', 1, 5, 1);
INSERT INTO words (id, en, sk, lesson, grade, user_id) VALUES (5, 'water', 'voda', 2, 5, 1);
INSERT INTO words (id, en, sk, lesson, grade, user_id) VALUES (6, 'fire', 'oheň', 2, 5, 1);
INSERT INTO words (id, en, sk, lesson, grade, user_id) VALUES (7, 'lion', 'lev', 3, 5, 1);
INSERT INTO words (id, en, sk, lesson, grade, user_id) VALUES (8, 'mom', 'mama', 1, 5, 2);
INSERT INTO words (id, en, sk, lesson, grade, user_id) VALUES (9, 'dad', 'tata', 1, 5, 2);
INSERT INTO words (id, en, sk, lesson, grade, user_id) VALUES (10, 'grandma', 'babka', 1, 5, 2);
INSERT INTO words (id, en, sk, lesson, grade, user_id) VALUES (11, 'grandpa', 'dedko', 1, 5, 2);
