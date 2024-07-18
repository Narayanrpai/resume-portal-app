INSERT INTO users (id, user_name, password, active, roles) values (1, 'foo', 'foo', true, 'USER');
INSERT INTO users (id, user_name, password, active, roles) values (2, 'bar', 'bar', true, 'USER');
INSERT INTO user_profile (id, user_name, theme, summary) values
(1, 'foo', '1', 'User name foo'),
(2, 'bar', '2', 'User name bar');