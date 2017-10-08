insert into t_users (
    usr_id,
    usr_first_name,
    usr_last_name,
    usr_login,
    usr_email,
    usr_password
) values (
    s_usr_id.nextval,
    'jacek',
    'golda',
    'jgolda',
    'jacek.golda.slask@gmail.com',
    'd034c688d372759e642f0bcec745839dabee3c4df1237186c8e6f9fe3178120dfdef13b33bb4403e'
);

insert into t_users (
    usr_id,
    usr_first_name,
    usr_last_name,
    usr_login,
    usr_email,
    usr_password
) values (
    s_usr_id.nextval,
    'root',
    'root',
    'root',
    'root@root.root',
    'fc32d8053c9429f9fe2e96223a293deacaa10b264363ffe1363d6bb9586db5635b6c5302cf6f078b'
);