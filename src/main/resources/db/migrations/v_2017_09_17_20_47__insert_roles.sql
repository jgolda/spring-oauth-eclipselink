declare
  v_rol_id_manager_readonly number(10) :=  s_rol_id.nextval;
  v_rol_id_manager number(10) :=  s_rol_id.nextval;
  v_rol_id_root number(10) := s_rol_id.nextval;

  v_prv_id_view_user_list number(10);
  v_prv_id_view_user number(10);
  v_prv_id_modify_user number(10);
  v_prv_id_view_privilege number(10);
begin
  insert into t_roles (rol_id, rol_code, rol_description) values (v_rol_id_manager_readonly, 'USER_MANAGER_READONLY', 'management of application users - readonly');
  insert into t_roles (rol_id, rol_code, rol_description) values (v_rol_id_manager, 'USER_MANAGER', 'management of application users');
  insert into t_roles (rol_id, rol_code, rol_description) values (v_rol_id_root, 'ROOT', 'unlimited access');

  select prv_id
    into v_prv_id_modify_user
    from t_privileges
    where prv_code = 'MODIFY_USER';

  select prv_id
    into v_prv_id_view_user
    from t_privileges
    where prv_code = 'VIEW_USER';

  select prv_id
    into v_prv_id_view_user_list
    from t_privileges
    where prv_code = 'VIEW_USER_LIST';

  select prv_id
    into v_prv_id_view_privilege
    from t_privileges
    where prv_code = 'VIEW_PRIVILEGE';

  insert into t_role_privileges (rpv_id, rpv_rol_id, rpv_prv_id) values (s_rpv_id.nextval, v_rol_id_manager_readonly, v_prv_id_view_user_list);
  insert into t_role_privileges (rpv_id, rpv_rol_id, rpv_prv_id) values (s_rpv_id.nextval, v_rol_id_manager_readonly, v_prv_id_view_user);
  insert into t_role_privileges (rpv_id, rpv_rol_id, rpv_prv_id) values (s_rpv_id.nextval, v_rol_id_manager, v_prv_id_view_user_list);
  insert into t_role_privileges (rpv_id, rpv_rol_id, rpv_prv_id) values (s_rpv_id.nextval, v_rol_id_manager, v_prv_id_view_user);
  insert into t_role_privileges (rpv_id, rpv_rol_id, rpv_prv_id) values (s_rpv_id.nextval, v_rol_id_manager, v_prv_id_modify_user);
  insert into t_role_privileges (rpv_id, rpv_rol_id, rpv_prv_id) values (s_rpv_id.nextval, v_rol_id_root, v_prv_id_view_user_list);
  insert into t_role_privileges (rpv_id, rpv_rol_id, rpv_prv_id) values (s_rpv_id.nextval, v_rol_id_root, v_prv_id_view_user);
  insert into t_role_privileges (rpv_id, rpv_rol_id, rpv_prv_id) values (s_rpv_id.nextval, v_rol_id_root, v_prv_id_modify_user);
  insert into t_role_privileges (rpv_id, rpv_rol_id, rpv_prv_id) values (s_rpv_id.nextval, v_rol_id_root, v_prv_id_view_privilege);

end;

