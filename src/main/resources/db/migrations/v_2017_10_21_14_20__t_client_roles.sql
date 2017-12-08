create table t_client_roles (
    crl_id number(10),
    crl_rol_id number(10),
    crl_apc_id number(10),

    constraint pk_t_client_roles
    primary key (crl_id),
    constraint fk_crl_rol_id
    foreign key (crl_rol_id)
    references t_roles (rol_id),
    constraint fk_crl_apc_id
    foreign key (crl_apc_id)
    references t_application_clients(apc_id)
);

create sequence s_crl_id
start with 1
increment by 1
nomaxvalue
nocache;

create trigger trg_crl_id
before insert on t_client_roles
for each row
    begin
        select s_crl_id.nextval
        into :new.crl_id
        from dual;
    end;