create table SIDE_MISSION_REPORT (
  report_id serial primary key,
  sm_type_id varchar(32) not null references side_mission_type(sm_type_id),
  performing_user_id varchar(21) not null references USERS (user_id),
  reporting_user_id varchar(21) not null references USERS (user_id),
  report_time timestamp default current_timestamp,
  rated boolean not null default false,
  valid boolean not null default true
);

create table SIDE_MISSION_REPORT_PROOF (
  proof_id serial primary key,
  report_id integer not null references SIDE_MISSION_REPORT(report_id),
  proof_type varchar(32) not null check (proof_type in ('PHOTO', 'VIDEO')),
  proof_url varchar(500) not null
);
