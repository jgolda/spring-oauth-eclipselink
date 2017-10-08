create table t_role_privileges (
    rpv_id number(10),
    rpv_rol_id number(10),
    rpv_prv_id number(10),

    constraint pk_t_role_privileges
    primary key (rpv_id),
    constraint fk_rpv_rol_id
    foreign key (rpv_rol_id)
    references t_roles (rol_id),
    constraint fk_rpv_prv_id
    foreign key (rpv_prv_id)
    references t_privileges (prv_id),
    constraint uq_rpv
    unique (rpv_rol_id, rpv_prv_id)
);

create sequence s_rpv_id
start with 1
increment by 1
nomaxvalue
nocache;

create trigger trg_rpv_id
before insert on t_role_privileges
for each row
    begin
        select s_rpv_id.nextval
        into :new.rpv_id
        from dual;
    end;