/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/7/17 17:44:13                           */
/*==============================================================*/


drop table if exists administrator;

drop table if exists department;

drop table if exists employee;

/*==============================================================*/
/* Table: administrator                                         */
/*==============================================================*/
create table administrator
(
   uid                  int not null auto_increment,
   uname                varchar(64) not null,
   password             varchar(64) not null,
   primary key (uid)
);

insert into administrator(uname,password) values("Giccqer","123456");

/*==============================================================*/
/* Table: department                                            */
/*==============================================================*/
create table department
(
   did                  int not null auto_increment,
   dname                varchar(64) not null,
   primary key (did)
);

insert into department values(1,'���Ʋ�');
insert into department values(2,'�鱨��');
insert into department values(3,'������');
insert into department values(4,'��ѵ��');

/*==============================================================*/
/* Table: employee                                              */
/*==============================================================*/
create table employee
(
   eid                  int not null auto_increment,
   ename                varchar(64) not null,
   age                  int,
   gender               varchar(32),
   email                varchar(64),
   deptId               int,
   primary key (eid)
);

insert into employee(ename,age,gender,email,deptid) values('������',22,'��','1111111111@qq.com',1);
insert into employee(ename,age,gender,email,deptid) values('��',24,'Ů','2222222222@qq.com',2);
insert into employee(ename,age,gender,email,deptid) values('����',34,'��','3333333333@qq.com',3);
insert into employee(ename,age,gender,email,deptid) values('����',21,'Ů','4444444444@qq.com',4);

alter table employee add constraint FK_Reference_1 foreign key (deptId)
      references department (did) on delete restrict on update restrict;

