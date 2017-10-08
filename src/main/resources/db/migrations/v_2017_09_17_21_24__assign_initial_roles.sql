declare
  v_rol_id_root number(10);
  v_rol_id_manager number(10);

  v_usr_id_root number(10);
  v_usr_id_manager number(10);
begin
  select rol_id
    into v_rol_id_manager
    from t_roles
    where rol_code = 'USER_MANAGER';

  select rol_id
    into v_rol_id_root
    from t_roles
    where rol_code = 'ROOT';

  select usr_id
    into v_usr_id_root
    from t_users
    where usr_login = 'root';

  select usr_id
    into v_usr_id_manager
    from t_users
    where usr_login = 'jgolda';

  insert into t_user_roles (url_id, url_rol_id, url_usr_id) values (s_url_id.nextval, v_rol_id_root, v_usr_id_root);
  insert into t_user_roles (url_id, url_rol_id, url_usr_id) values (s_url_id.nextval, v_rol_id_manager, v_usr_id_manager);

end;

