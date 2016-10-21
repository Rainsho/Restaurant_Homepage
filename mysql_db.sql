/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/10/21 17:26:01                          */
/*==============================================================*/


drop table if exists food;

drop table if exists foodtype;

drop table if exists homepage;

drop table if exists news;

drop table if exists orderdetail;

drop table if exists orders;

drop table if exists picture;

drop table if exists reservation;

drop table if exists users;

/*==============================================================*/
/* Table: food                                                  */
/*==============================================================*/
create table food
(
   fid                  int not null auto_increment,
   ftid                 int,
   pid                  int,
   fname                varchar(200) not null,
   fdetial              varchar(1000) not null,
   fprice               decimal(8,2) not null,
   primary key (fid)
);

/*==============================================================*/
/* Table: foodtype                                              */
/*==============================================================*/
create table foodtype
(
   ftid                 int not null auto_increment,
   ftname               varchar(200) not null,
   primary key (ftid)
);

/*==============================================================*/
/* Table: homepage                                              */
/*==============================================================*/
create table homepage
(
   hid                  int not null auto_increment,
   pic                  varchar(200),
   title                varchar(200),
   contents             varchar(2000),
   type                 varchar(50),
   primary key (hid)
);

/*==============================================================*/
/* Table: news                                                  */
/*==============================================================*/
create table news
(
   nid                  int not null auto_increment,
   nauthor              varchar(200) not null,
   ndate                datetime not null,
   ncontent             varchar(8000) not null,
   ntag                 varchar(200) not null,
   primary key (nid)
);

/*==============================================================*/
/* Table: orderdetail                                           */
/*==============================================================*/
create table orderdetail
(
   oid                  int not null,
   fid                  int not null,
   odquant              int not null,
   primary key (oid, fid)
);

/*==============================================================*/
/* Table: orders                                                */
/*==============================================================*/
create table orders
(
   oid                  int not null auto_increment,
   odate                datetime not null,
   oquant               int not null,
   ofee                 decimal(8,2) not null,
   ocheck               tinyint not null default 0,
   primary key (oid)
);

/*==============================================================*/
/* Table: picture                                               */
/*==============================================================*/
create table picture
(
   pid                  int not null auto_increment,
   pname                varchar(200) not null,
   ppath                varchar(200) not null,
   pdisplay             tinyint not null default 0,
   primary key (pid)
);

/*==============================================================*/
/* Table: reservation                                           */
/*==============================================================*/
create table reservation
(
   resid                int not null auto_increment,
   uid                  int,
   restitle             varchar(200) not null,
   resdate              datetime not null,
   resinfo              varchar(2000) not null,
   resseat              int not null,
   primary key (resid)
);

/*==============================================================*/
/* Table: users                                                 */
/*==============================================================*/
create table users
(
   uid                  int not null auto_increment,
   uname                varchar(50) not null,
   upassword            varchar(50),
   utelphone            varchar(50),
   utype                tinyint not null default 3,
   ustaffinfo           varchar(2000),
   ustaffdisplay        tinyint not null default 0,
   upic                 varchar(200),
   primary key (uid)
);

alter table food add constraint FK_Relationship_1 foreign key (ftid)
      references foodtype (ftid) on delete restrict on update restrict;

alter table food add constraint FK_Relationship_3 foreign key (pid)
      references picture (pid) on delete restrict on update restrict;

alter table orderdetail add constraint FK_Relationship_4 foreign key (fid)
      references food (fid) on delete restrict on update restrict;

alter table orderdetail add constraint FK_Relationship_5 foreign key (oid)
      references orders (oid) on delete restrict on update restrict;

alter table reservation add constraint FK_Relationship_2 foreign key (uid)
      references users (uid) on delete restrict on update restrict;

