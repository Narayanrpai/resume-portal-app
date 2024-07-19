INSERT INTO users (id, user_name, password, active, roles) values (1, 'einstein', 'einstein', true, 'USER');
INSERT INTO users (id, user_name, password, active, roles) values (2, 'newton', 'newton', true, 'USER');
INSERT INTO user_profile (id, user_name, theme, summary, first_name, last_name, email, phone, designation) values
(1, 'einstein', '1', 'Best known for developing the theory of relativity, Einstein also made important contributions to quantum mechanics.', 'Albert', 'Einstein','einstein@gmail.com','111-111-1111','Theoretical Physicist.'),
(2, 'newton', '2', ' He was a key figure in the Scientific Revolution and the Enlightenment that followed. His pioneering book Philosophiæ Naturalis Principia Mathematica.', 'Isac', 'Newton','newton@gmail.com','611-121-1441','Mathematician, physicist, astronomer, theologian and author.');
