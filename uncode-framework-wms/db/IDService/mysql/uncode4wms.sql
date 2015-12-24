drop table if exists Employee;
create table Employee (
     ID varchar(50) not null comment 'ID',
     EmployeeENL varchar(8) not null comment '员工编号',
     EmployeeName varchar(50) not null comment '员工姓名EN',
     EmployeeNameLL varchar(50) comment '员工姓名ZH',
     Email varchar(100) comment 'Email地址',
     Status varchar(1) default 1 comment '状态：0 未启用 1 启用',
     MobilePhone varchar(50) comment '座机',
     SupervisorENL varchar(8) comment '上级',
     Position varchar(20) comment '职位',
     TelPhone varchar(20) comment '移动手机',
     primary key (ID)
) engine=innodb default charset=utf8 collate=utf8_unicode_ci comment='员工信息表';

insert into Employee (ID, EmployeeENL, EmployeeName, EmployeeNameLL) values ('10000', 'UNCODE', 'Xie Xiao', '谢晓');

drop table if exists Role;
create table Role (
	ID varchar(50) not null comment 'ID',
	RoleName varchar(100) not null comment '角色名称',
	Description varchar(200) comment '角色描述',
	Status int default 1 comment '状态：0 未启用 1 启用',
	primary key (ID)
) engine=innodb default charset=utf8 collate=utf8_unicode_ci comment='角色表';

insert into Role(ID, RoleName, Description) values ('10000', 'Admin', '系统管理员');

drop table if exists EmployeeRole;
create table EmployeeRole (
	EmployeeID varchar(50) not null comment '员工ID',
	RoleID varchar(50) not null comment '角色ID'
) engine=innodb default charset=utf8 collate=utf8_unicode_ci comment='员工角色关联表';

insert into EmployeeRole (EmployeeID, RoleID) values ('10000', '10000');

drop table if exists Menu;
create table Menu (
	ID varchar(50) not null comment 'ID',
	MenuName varchar(50) not null comment '菜单名称',
	MenuNameEN varchar(50) not null comment '菜单名称EN',
	PanelName varchar(50) comment '面板名称',
	Position int comment '位置',
	ParentID varchar(50) comment '父菜单',
	Status int default 1 comment '状态：0 未启用 1 启用',
	primary key (ID)
) engine=innodb default charset=utf8 collate=utf8_unicode_ci comment='菜单表';

insert into Menu (ID, MenuName, MenuNameEN, PanelName, Position, ParentID, Status) values ('10000', '系统管理', 'System Management', '#', '1', '-1', 1);
insert into Menu (ID, MenuName, MenuNameEN, PanelName, Position, ParentID, Status) values ('10001', '菜单管理', 'Menu Management', 'menuManagement', '2', '10000', 1);

drop table if exists MenuRole;
create table MenuRole (
	MenuID varchar(50) not null comment '菜单ID',
	RoleID varchar(50) not null comment '角色ID'
) engine=innodb default charset=utf8 collate=utf8_unicode_ci comment='菜单角色关联表';

insert into MenuRole (MenuID, RoleID) values ('10000', '10000');