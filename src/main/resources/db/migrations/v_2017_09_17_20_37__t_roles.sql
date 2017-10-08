create table t_roles (
    rol_id number(10),
    rol_code varchar2(50),
    rol_description varchar2(200),

    constraint pk_t_roles
    primary key (rol_id),
    constraint uq_rol_code
    unique (rol_code)
);

create sequence s_rol_id
start with 1
increment by 1
nomaxvalue
nocache;