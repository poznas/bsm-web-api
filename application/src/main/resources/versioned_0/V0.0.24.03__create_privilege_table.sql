
create table PRIVILEGE (
  user_id varchar(21) references USERS (user_id),
  privilege_id varchar(40) not null,
  PRIMARY KEY (user_id, privilege_id)
);
