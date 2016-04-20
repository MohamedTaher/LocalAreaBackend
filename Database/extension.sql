
drop table comments;
drop table likes;
drop table checkins;
drop table places;


create table places(
	id int(11) PRIMARY KEY,
	name varchar(100),
	description varchar(500),
	lng double(14, 4),
	lat double(14, 4),
	userID int(11),
	numberOfCheckins int(11)
);

alter table places
add foreign key(userID) references users(id);
alter table places
modify column id INT auto_increment;
alter table places add rateSum int(11);
alter table places add userNum int(11);
alter table places modify column rateSum int(11) default 0.0;
alter table places modify column userNum int(11) default 0.0;




create table checkins(
	id int(11) PRIMARY KEY,
	description varchar(500),
	checkintime timestamp,
	userID int(11),
	placeID int(11),
	likes int(11),
	comments int(11)
);


alter table checkins
add foreign key(placeID) references places(id);

alter table checkins
modify column id INT auto_increment;

alter table checkins
add foreign key(userID) references users(id);





create table likes(
	id int(11) primary key,
	userID int(11),
	checkinID int(11)
);


alter table likes
add foreign key(userID) references users(id);
alter table likes
add foreign key(checkinID) references checkins(id);
alter table likes
modify column id INT auto_increment;



create table comments(
	id int(11) PRIMARY KEY,
	userID int(11),
	checkinID int(11),
	description varchar(500)
);


alter table comments
add foreign key(userID) references users(id);
alter table comments
add foreign key(checkinID) references checkins(id);
alter table comments
modify column id INT auto_increment;














insert into users(id, name, email, password, lat, users.long) values(101, "kareem", "kemail", "kassword", 30.0, 30.0);
insert into places(id, name, description, lng, lat, userID, numberOfCheckins, rateSum, userNum) values(111, "kareemplace", "kareemdescription", 31.1213, 30.13213, 101, 1, 6, 13);
insert into places(id, name, description, lng, lat, userID, numberOfCheckins, rateSum, userNum) values(222, "kareemplace2", "kareemdescription2", 32.1213, 30.13213, 101, 2, 7, 14);
insert into places(id, name, description, lng, lat, userID, numberOfCheckins, rateSum, userNum) values(333, "kareemplace3", "kareemdescription3", 31.1213, 29.13213, 101, 3, 123, 14);
insert into places(id, name, description, lng, lat, userID, numberOfCheckins, rateSum, userNum) values(444, "kareemplace4", "kareemdescription4", 31.1213, 30.13213, 101, 2, 6, 13);
insert into checkins(description, userID, placeID, likes, comments) values("kcheckin1", 101, 111, 1, 2);
insert into checkins(description, userID, placeID, likes, comments) values("kcheckin2", 101, 222, 1, 2);
insert into checkins(description, userID, placeID, likes, comments) values("kcheckin3", 101, 333, 1, 2);
insert into checkins(description, userID, placeID, likes, comments) values("kcheckin4", 101, 444, 1, 2);
insert into checkins(description, userID, placeID, likes, comments) values("kcheckin5", 101, 222, 1, 2);
insert into checkins(description, userID, placeID, likes, comments) values("kcheckin6", 101, 111, 1, 2);
insert into users(id, name, email, password, lat, users.long) values(102, "meerak", "merrak", "krkr", 12, 13);
insert into users(id, name, email, password, lat, users.long) values(103, "2meerak", "2merrak", "2krkr", 12, 13);
insert into follow(follower, followed) values(101, 102);
insert into follow(follower, followed) values(101, 103);
insert into checkins(description, userID, placeID, likes, comments) values("kcheckin6", 102, 111, 1, 2);
insert into checkins(description, userID, placeID, likes, comments) values("kcheckin6", 103, 111, 1, 2);
