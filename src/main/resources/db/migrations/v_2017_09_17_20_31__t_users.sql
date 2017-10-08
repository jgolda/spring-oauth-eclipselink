create table T_USERS (
  usr_id number(10),
  usr_login varchar2(100),
  usr_email varchar2(256),
  usr_password varchar2(100),
  usr_first_name nvarchar2(200),
  usr_last_name nvarchar2(200),

  constraint pk_t_users
    primary key (usr_id)
);

create sequence s_usr_id
start with 1
increment by 1
nomaxvalue
nocache;