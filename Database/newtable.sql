create table userPlaces(

     userID int(11),
     plaseID int (11)
	
);

alter table userPlaces
add foreign key(userID) references users(id);

alter table userPlaces
add foreign key(plaseID) references places(id);



///////////////////////////////////////////

create table likeNotefication(

     toUserID int(11),
     fromUserID int (11),
     chINID int(11)
	
);


alter table likeNotefication
add foreign key(toUserID) references users(id);

alter table likeNotefication
add foreign key(fromUserID) references users(id);

alter table likeNotefication
add foreign key(chINID) references checkins(id);

/////////////////////////////////////////////

create table commentNotefication(

     toUserID int(11),
     fromUserID int (11),
     chINID int(11)
	
);


alter table commentNotefication
add foreign key(toUserID) references users(id);

alter table commentNotefication
add foreign key(fromUserID) references users(id);

alter table commentNotefication
add foreign key(chINID) references checkins(id);
//////////////////////////////////////////////
create table actions(

userid int(11),
checkinid int(11),
type varchar(50)

);
alter table actions
add foreign key(userid) references users(id);


alter table actions
add foreign key(checkinid) references checkins(id);




