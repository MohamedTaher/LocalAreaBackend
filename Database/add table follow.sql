CREATE TABLE follow(
	follower int(11) NOT NULL,
	followed int(11) NOT NULL
);

ALTER TABLE follow
ADD FOREIGN KEY(follower) REFERENCES users(id);


ALTER TABLE follow
ADD FOREIGN KEY(followed) REFERENCES users(id);
