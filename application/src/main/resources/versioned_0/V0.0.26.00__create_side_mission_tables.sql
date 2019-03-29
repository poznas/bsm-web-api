create table SIDE_MISSION_TYPE (
  sm_type_id varchar(32) primary key,
  description_document_url varchar(500),
  equation varchar(500) not null,
  dictionary_name varchar(128)
);

create table SIDE_MISSION_PROOF_REQ (
  id serial primary key,
  sm_type_id varchar(32) not null references SIDE_MISSION_TYPE(sm_type_id),
  proof_req_type varchar(32) not null,
  proof_example_url varchar(500),
  hint_dict_key varchar(128)
);

create table SIDE_MISSION_PERFORM_PARAM (
  sm_type_id varchar(32) not null references SIDE_MISSION_TYPE(sm_type_id),
  equation_symbol varchar(8) not null,
  perform_param_type varchar(24) not null,
  to_judge_by varchar(16) not null check (to_judge_by in ('PROFESSOR', 'JUDGE')),
  hint_dict_key varchar(128),
  available_values_source varchar(2400),
  primary key (sm_type_id, equation_symbol)
);
