 create table if not exists user_profile (
 user_id varchar(500) not null constraint user_jpa_data_store_credential_pkey primary key,
 name varchar(500),
 email_id varchar(500) not null,
 org varchar(500),
 created_at timestamp default now() not null,
 updated_at timestamp default now() not null
 );

 create table if not exists user_submission (
  submission_id varchar(500) not null constraint problem_jpa_data_store_credential_pkey primary_key,
  user_id varchar(500) not null,
  problem_link varchar(500) not null,
  solution_link varchar(500) not null,
  created_at timestamp default now() not null,
  updated_at timestamp default now() not null
 );

create table if not exists problem_desc {
  problem_id varchar(500),
  url varchar(500) not null,
  description varchar not null,
  created_at timestamp default now() not null,
  updated_at timestamp default now() not null
}