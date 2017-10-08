create table t_user_roles (
    url_id number(10),
    url_rol_id number(10),
    url_usr_id number(10),

    constraint pk_t_user_roles
    primary key (url_id),
    constraint fk_url_rol_id
    foreign key (url_rol_id)
    references t_roles (rol_id),
    constraint fk_url_usr_id
    foreign key (url_usr_id)
    references t_users (usr_id)
);

create sequence s_url_id
start with 1
increment by 1
nomaxvalue
nocache;

create trigger trg_urr_id
before insert on t_user_roles
for each row
    begin
        select s_url_id.nextval
        into :new.url_id
        from dual;
    end;