 create table if not exists user_profile (
 user_id varchar(500) not null constraint user_jpa_data_store_credential_pkey primary key,
 name varchar(500),
 email_id varchar(500) not null,
 org varchar(500),
 referrer_id varchar(500) not null,
 created_at timestamp default now() not null,
 updated_at timestamp default now() not null
 );

 create table if not exists user_submission (
  submission_id varchar(500) not null constraint submission_jpa_data_store_credential_pkey primary key,
  user_id varchar(500) not null,
  problem_name varchar(500) not null,
  problem_link varchar(500) not null,
  solution_link varchar(500) not null,
  created_at timestamp default now() not null,
  updated_at timestamp default now() not null
 );

create table if not exists problem_description (
  problem_id varchar(500) not null constraint problem_jpa_data_store_credential_pkey primary key,
  title varchar(500) not null,
  url varchar(500) not null,
  description varchar,
  complexity varchar(100),
  example varchar(500),
  problem_constraints varchar(500),
  idx int not null,
  slug_title varchar(500),
  created_at timestamp default now() not null,
  updated_at timestamp default now() not null
);

create table if not exists problem_input (
    id varchar(500) not null constraint problem_input_jpa_data_store_credential_pkey primary key,
    problem_id varchar(500) not null,
    argument varchar not null,
    output varchar not null,
    created_at timestamp default now() not null,
    updated_at timestamp default now() not null
);

create table if not exists problem_base_code (
    id varchar(500) not null constraint problem_base_code_jpa_data_store_credential_pkey primary key,
    problem_id varchar(500) not null,
    language varchar(500) not null,
    base_code varchar not null,
    created_at timestamp default now() not null,
    updated_at timestamp default now() not null
);

create table if not exists post_comment (
    comment_id varchar(500) not null constraint post_comment_jpa_data_store_credential_pkey primary key,
    post_id varchar(500) not null,
    author_id varchar(500) not null,
    text varchar,
    created_at timestamp default now() not null,
    updated_at timestamp default now() not null
);

create table if not exists code_submission (
    submission_id varchar(500) not null constraint code_submission_jpa_data_store_credential_pkey primary key,
    user_id varchar(500) not null,
    problem_id varchar(500) not null,
    language varchar(500) not null,
    solution_code varchar not null,
    accepted boolean not null,
    running_time_ms int not null,
    memory_consumed int not null,
    created_at timestamp default now() not null,
    updated_at timestamp default now() not null
);

create table if not exists user_post (
    post_id varchar(500) not null constraint user_post_jpa_data_store_credential_pkey primary key,
    author_id varchar(500) not null,
    problem_id varchar(500),
    post_type varchar(500) not null,
    text varchar,
    img_url varchar(500),
    video_url varchar(500),
    problem_name varchar(500),
    language_name varchar(500),
    created_at timestamp default now() not null,
    updated_at timestamp default now() not null
);

create table if not exists post_like (
    like_id varchar(500) not null constraint post_like_jpa_data_store_credential_pkey primary key,
    post_id varchar(500) not null,
    author_id varchar(500) not null,
    is_like boolean default false,
    seen boolean default false not null,
    created_at timestamp default now() not null,
    updated_at timestamp default now() not null
);