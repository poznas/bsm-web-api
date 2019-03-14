
alter table TEAM
alter column team_color type char(7),
add column playing boolean not null;

comment on column team.playing is 'Indicates if active players team';
comment on column team.team_color is '#HEX color notation';
