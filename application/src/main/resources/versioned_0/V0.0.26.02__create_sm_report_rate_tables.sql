
alter table SIDE_MISSION_PERFORM_PARAM rename column to_judge_by to to_rate_by;

create table SM_REPORT_RATE (
  sm_type_id varchar(32) not null references SIDE_MISSION_TYPE(sm_type_id),
  report_id integer not null references SIDE_MISSION_REPORT(report_id),
  perform_param_symbol varchar(8) not null,
  rate_value decimal not null,
  rater_user_id varchar(21) not null references USERS (user_id),
  foreign key (sm_type_id, perform_param_symbol) references SIDE_MISSION_PERFORM_PARAM(sm_type_id, equation_symbol),
  primary key(report_id, perform_param_symbol,rater_user_id)
);
