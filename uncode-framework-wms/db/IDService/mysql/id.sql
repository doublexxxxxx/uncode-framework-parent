delimiter ;
-- sequence 管理表
drop table if exists sequence;
create table sequence (
         name varchar(50) not null,
         current_value int not null,
         increment int not null default 1,
         primary key (name)
) engine=innodb default charset=utf8;
insert into sequence values ('idseq', 1000, 1);
 
-- 取当前值的函数
drop function if exists currval;
delimiter $
create function currval (seq_name varchar(50))
         returns integer
         language sql
         deterministic
         contains sql
         sql security definer
         comment ''
begin
         declare value integer;
         set value = 0;
         select current_value into value   from sequence  where name = seq_name;
         return value;
end
$
delimiter ;
 
-- 取下一个值的函数
drop function if exists nextval;
delimiter $
create function nextval (seq_name varchar(50))
         returns integer
         language sql
         deterministic
         contains sql
         sql security definer
         comment ''
begin
         update sequence
                   set current_value = current_value + increment
                   where name = seq_name;
         return currval(seq_name);
end
$
delimiter ;
 
-- 更新当前值的函数
drop function if exists setval;
delimiter $
create function setval (seq_name varchar(50), value integer)
         returns integer
         language sql
         deterministic
         contains sql
         sql security definer
         comment ''
begin
         update sequence
                   set current_value = value
                   where name = seq_name;
         return currval(seq_name);
end
$
delimiter ;


drop procedure if exists sp_id_generator;
delimiter $
create procedure sp_id_generator (in numids int, out beginid int, out endid int)
	comment 'id generator'
begin
	declare currentid int default 0; 
	-- get current value
	select currval('idseq') into currentid;
	set beginid = currentid + 1;
	set endid = currentid + numids;
	-- set last value
	select setval('idseq', endid);
end
$
delimiter ;