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