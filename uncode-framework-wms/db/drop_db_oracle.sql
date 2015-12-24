--drop user
drop USER dsb cascade;
--drop role
drop ROLE dsb_role;
--drop tablespace
drop TABLESPACE dsb_tabspaces including contents and datafiles;
commit;
/* */