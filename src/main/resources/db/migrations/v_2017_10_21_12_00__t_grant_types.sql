create table t_grant_types (
  gtt_id number(10),
  gtt_apc_id number(10),
  gtt_name varchar(200),

  constraint pk_t_grant_types
  primary key (gtt_id),
  constraint uq_grant_type
  unique (gtt_apc_id, gtt_name)
);

create sequence s_gtt_id
start with 1
increment by 1
nomaxvalue
nocache;

create or replace trigger trg_gtt_id
  before insert on t_grant_types
  for each row
  begin
    select s_gtt_id.nextval
      into :new.gtt_id
      from dual;
  end;