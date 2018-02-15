create table T_WORDS (
  wrd_id number(10),
  wrd_word varchar2(250),
  wrd_name varchar2(256),

  constraint pk_t_words
    primary key (wrd_id)
);

create sequence s_wrd_id
start with 1
increment by 1
nomaxvalue
nocache;