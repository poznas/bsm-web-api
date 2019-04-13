
create table POINTS (
  points_id integer not null,
  points_amount decimal not null,
  points_type varchar(32) not null check (points_type in
  ('SIDE_MISSION', 'MAIN_COMPETITION', 'MEDAL', 'BADGE', 'BET_WIN', 'BET_LOSS')),
  points_short_label varchar(64),
  team_id varchar(15) not null references TEAM(team_id),
  user_id varchar(21) references USERS(user_id),
  insert_time timestamp default current_timestamp,
  valid boolean not null default true,
  primary key (points_id, points_type)
);
