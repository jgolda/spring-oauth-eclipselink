create table t_application_clients (
  apc_id number(10),
  apc_code varchar2(50),
  apc_name varchar(200),
  apc_client_id varchar2(500),
  apc_is_secret_required VARCHAR2(1),
  apc_client_secret varchar2(100),
  apc_is_scoped VARCHAR2(1),
  apc_token_validity_seconds NUMBER(9),


  constraint pk_t_application_clients
  primary key (apc_id),
  constraint uq_apc_code
  unique (apc_code)
);

create sequence s_apc_id
start with 1
increment by 1
nomaxvalue
nocache;