INSERT INTO screening_rooms values (1);
INSERT INTO screening_rooms values (2);
INSERT INTO screening_rooms values (3);

INSERT INTO movies (title) values ('Fantastyczne zwierzęta: Zbrodnie Grindenwalda');
INSERT INTO movies (title) values ('Doktor Strange w multiwersum obłędu');
INSERT INTO movies (title) values ('Batman');
INSERT INTO movies (title) values ('Thor: Miłość i grom');

INSERT INTO screenings (start_time, screening_room_id, movie_id) values (parsedatetime('2022-05-01 16:00', 'yyyy-MM-dd HH:mm'), 1, 1);
INSERT INTO screenings (start_time, screening_room_id, movie_id) values (parsedatetime('2022-05-01 19:30', 'yyyy-MM-dd HH:mm'), 1, 4);
INSERT INTO screenings (start_time, screening_room_id, movie_id) values (parsedatetime('2022-05-01 14:00', 'yyyy-MM-dd HH:mm'), 2, 1);
INSERT INTO screenings (start_time, screening_room_id, movie_id) values (parsedatetime('2022-05-01 17:00', 'yyyy-MM-dd HH:mm'), 2, 2);
INSERT INTO screenings (start_time, screening_room_id, movie_id) values (parsedatetime('2022-05-01 20:45', 'yyyy-MM-dd HH:mm'), 2, 3);
INSERT INTO screenings (start_time, screening_room_id, movie_id) values (parsedatetime('2022-05-01 15:00', 'yyyy-MM-dd HH:mm'), 3, 2);
INSERT INTO screenings (start_time, screening_room_id, movie_id) values (parsedatetime('2022-05-01 20:20', 'yyyy-MM-dd HH:mm'), 3, 4);

INSERT INTO tickets (type, price) values ('adult', 2500);
INSERT INTO tickets (type, price) values ('student', 1800);
INSERT INTO tickets (type, price) values ('child', 1250);

INSERT INTO seats (screening_room_id, line, column) values (1, 1, 1);
INSERT INTO seats (screening_room_id, line, column) values (1, 1, 2);
INSERT INTO seats (screening_room_id, line, column) values (2, 1, 1);
INSERT INTO seats (screening_room_id, line, column) values (2, 2, 1);
INSERT INTO seats (screening_room_id, line, column) values (3, 1, 1);
INSERT INTO seats (screening_room_id, line, column) values (3, 1, 2);
INSERT INTO seats (screening_room_id, line, column) values (3, 1, 3);
INSERT INTO seats (screening_room_id, line, column) values (3, 2, 1);
INSERT INTO seats (screening_room_id, line, column) values (3, 2, 2);
INSERT INTO seats (screening_room_id, line, column) values (3, 2, 3);

INSERT INTO constants (name, const_value) values ('expirationTimeMinutes', 15);