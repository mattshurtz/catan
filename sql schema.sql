create table if not exists CurrentGames (
	id int,
	version int,
	name text,
	player0_id int,
	player1_id int,
	player2_id int,
	player3_id int,
	state blob
);

create table if not exists Users (
	id int,
	username text,
	password text
);

create table if not exists Commands (
	id int,
	command text,
	json text,
	player_id int,
	game_id int,
	version int
);