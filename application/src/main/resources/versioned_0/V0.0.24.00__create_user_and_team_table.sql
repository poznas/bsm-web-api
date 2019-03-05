create table TEAM (
  team_id varchar(21) primary key,
  team_name varchar(30) not null,
  team_color char(9) not null
);

create unique index team_name_idx on TEAM (team_name);

create table USERS (
  user_id varchar(21) primary key,
  username varchar(30) not null,
  email varchar(100) not null,
  image_url varchar(500),
  team_id varchar(15) not null references TEAM(team_id)
);
