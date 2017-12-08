DECLARE
  v_password_apc_id t_application_clients.apc_id%type := s_apc_id.nextval;
  v_rol_id t_roles.rol_id%type;
BEGIN
  insert into t_application_clients (apc_id, apc_code, apc_name, apc_client_id, APC_IS_SCOPED, APC_CLIENT_SECRET, APC_IS_SECRET_REQUIRED, APC_TOKEN_VALIDITY_SECONDS)
    values (v_password_apc_id, 'JS_CLIENT', 'js client', 'jsclient', '1', '1bd0f839975fa440428c69f835b8e76c977de652e4a17dc60d808d7d342c0d621590c644c1bbb0a6', '1', 3600); -- password: jsclient

  insert into t_grant_types (gtt_id, gtt_apc_id, gtt_name)
    values (s_gtt_id.nextval, v_password_apc_id, 'PASSWORD');

  insert into t_grant_types (gtt_id, gtt_apc_id, gtt_name)
    values (s_gtt_id.nextval, v_password_apc_id, 'AUTHORIZATION_CODE');

  insert into t_scopes (scp_id, scp_apc_id, scp_name, scp_is_auto_approve)
  values (s_scp_id.nextval, v_password_apc_id, 'read', '1');

  select rol_id
    into v_rol_id
    from T_ROLES
    where rol_code = 'JS_API';

  insert into t_client_roles (crl_apc_id, crl_rol_id)
    values (v_password_apc_id, v_rol_id);
END;