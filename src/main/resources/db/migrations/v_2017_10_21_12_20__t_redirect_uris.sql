create table t_redirect_uris (
  rur_id number(10),
  rur_apc_id number(10),
  rur_uri varchar(500),

  constraint pk_t_redirect_uris
  primary key (rur_id),
  constraint uq_redirect_uris
  unique (rur_apc_id, rur_uri)
);

create sequence s_rur_id
start with 1
increment by 1
nomaxvalue
nocache;

create or replace trigger trg_rur_id
  before insert on t_redirect_uris
  for each row
  begin
    select s_rur_id.nextval
      into :new.rur_id
      from dual;
  end;