insert into actor values (1, 'Siddarth');
insert into actor values (2, 'Black');
insert into actor values (3, 'Steven');

insert into film(id, title, release_year, fun_facts,production_company,distributor, director, writer) values (1, 'St24 Hours on Craigslist', 2011, 'so fun', 'SPI Cinemas', 'General Film Company', 'Charls Chaplin', 'Charls Chaplin');
insert into film(id, title, release_year, fun_facts,production_company,distributor, director, writer) values (2, 'Ant Man', 2015, 'so fun', 'SPI Cinemas', 'General Film Company', 'Charls Chaplin', 'Charls Chaplin');

insert into location(id, name, latitude, longitude) values (1, 'Justin Herman Plaza', 37.7951195, -122.3952007);
insert into location(id, name, latitude, longitude) values (2, '200 block Market Street', 39.9498186, -75.14385089999999);
insert into location(id, name, latitude, longitude) values (3, 'City Hall', 37.7792597, -122.4192646);

insert into film_location(film_id, location_id) values(1, 1);
insert into film_location(film_id, location_id) values(1, 2);
insert into film_location(film_id, location_id) values(1, 3);

insert into film_actor(film_id, actor_id) values(1, 1);
insert into film_actor(film_id, actor_id) values(1, 2);
insert into film_actor(film_id, actor_id) values(1, 3);

