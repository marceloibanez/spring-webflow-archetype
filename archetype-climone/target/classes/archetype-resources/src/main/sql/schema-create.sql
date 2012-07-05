drop table if exists role_permissions
go

drop table if exists user_roles
go

drop table if exists user
go

drop table if exists role
go

drop table if exists permission
go

create table permission ( 
	name       	varchar(255) not null,
	description	varchar(255) not null,
	constraint pk_permission primary key (name)
	)
engine=innodb default charset=utf8	
go

create table role_permissions ( 
	role_id      	integer not null,
	per_name     	varchar(255) not null,
	constraint pk_role_perm primary key (role_id, per_name)
	)
engine=innodb default charset=utf8
go

create table role ( 
	id         	integer auto_increment not null,
	uid        	varchar(255) not null,
	name       	varchar(255) null,
	description	varchar(255) null,
	constraint pk_role primary key (id)
	)
engine=innodb default charset=utf8
go

create table user_roles ( 
	user_id      	integer not null,
	role_id      	integer not null,
	constraint pk_user_role primary key (user_id,role_id)
	)
engine=innodb default charset=utf8
go

create table user ( 
	id        	integer auto_increment not null,
	uid       	varchar(255) not null,
	first_name	varchar(255) null,
	last_name 	varchar(255) null,
	username  	varchar(255) not null,
	password  	varchar(255) not null,
	email 		varchar(255) not null,
	constraint pk_user primary key (id)
	)
engine=innodb default charset=utf8
go

alter table role_permissions
	add constraint fk_role_perm_role foreign key (role_id) references role(id)
go

alter table role_permissions
	add constraint fk_role_perm_perm foreign key (per_name) references permission(name)
go

alter table user_roles
	add constraint fk_user_role_role foreign key (role_id) references role(id)
go

alter table user_roles
	add constraint fk_user_role_user foreign key (user_id) references user(id)
go


insert into permission (name, description) values ("ADM_ROL","Administrar Roles")
go
insert into permission (name, description) values ("ADM_PERM","Administrar Permisos")
go
insert into permission (name, description) values ("ADM_USER","Administrar Usuarios")
go

insert into role (id,uid,name,description) values (1,"1","Administrador","Administrador del Sistema")
go

insert into role_permissions (role_id,per_name) values (1,"ADM_USER")
go
insert into role_permissions (role_id,per_name) values (1,"ADM_ROL")
go
insert into role_permissions (role_id,per_name) values (1,"ADM_PERM")
go

-- la password es "password"
insert into user (id,uid,first_name,last_name,username,password, email) values (1,"1","Admin","Admin","admin","f087755d012bbf2509d279a63cdfa055", "admin@mail.com")
go

insert into user_roles (user_id,role_id) values (1,1)
go