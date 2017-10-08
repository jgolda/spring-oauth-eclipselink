create table t_privileges (
    prv_id number(10),
    prv_code varchar2(50),
    prv_description varchar2(200),

    constraint pk_t_privileges
        primary key (prv_id),
    constraint uq_prv_code
        UNIQUE (prv_code)
);

create sequence s_prv_id
start with 1
increment by 1
nomaxvalue
nocache;