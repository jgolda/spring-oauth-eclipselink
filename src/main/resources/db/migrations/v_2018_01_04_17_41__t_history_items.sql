create table t_history_items (
  hit_id number(10),
  hit_owner_id number(10),
  hit_class_name varchar2(500),
  hit_field_name varchar2(200),
  hit_old_value varchar2(2000),
  hit_new_value varchar2(2000),

  constraint pk_t_history_items
    primary key (hit_id)
);

create sequence s_hit_id
start with 1
increment by 1
nomaxvalue
nocache;