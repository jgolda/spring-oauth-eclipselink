create table t_scopes (
  scp_id number(10),
  scp_apc_id number(10),
  scp_name varchar(200),
  scp_is_auto_approve varchar2(1),

  constraint pk_t_scopes
  primary key (scp_id),
  constraint uq_scope
  unique (scp_apc_id, scp_name)
);

create sequence s_scp_id
start with 1
increment by 1
nomaxvalue
nocache;