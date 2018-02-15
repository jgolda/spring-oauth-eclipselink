create table T_LANGUAGES (
  lng_id number(10),
  lng_code varchar2(100),
  lng_name varchar2(256),

  constraint pk_t_languages
    primary key (lng_id)
);

create sequence s_lng_id
start with 1
increment by 1
nomaxvalue
nocache;