/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2000                    */
/* Created on:     2016/10/21 16:48:41                          */
/*==============================================================*/


if exists (select 1
   from dbo.sysreferences r join dbo.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('food') and o.name = 'FK_FOOD_RELATIONS_FOODTYPE')
alter table food
   drop constraint FK_FOOD_RELATIONS_FOODTYPE
go

if exists (select 1
   from dbo.sysreferences r join dbo.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('food') and o.name = 'FK_FOOD_RELATIONS_PICTURE')
alter table food
   drop constraint FK_FOOD_RELATIONS_PICTURE
go

if exists (select 1
   from dbo.sysreferences r join dbo.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('orderdetail') and o.name = 'FK_ORDERDET_RELATIONS_FOOD')
alter table orderdetail
   drop constraint FK_ORDERDET_RELATIONS_FOOD
go

if exists (select 1
   from dbo.sysreferences r join dbo.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('orderdetail') and o.name = 'FK_ORDERDET_RELATIONS_ORDERS')
alter table orderdetail
   drop constraint FK_ORDERDET_RELATIONS_ORDERS
go

if exists (select 1
   from dbo.sysreferences r join dbo.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('reservation') and o.name = 'FK_RESERVAT_RELATIONS_USERS')
alter table reservation
   drop constraint FK_RESERVAT_RELATIONS_USERS
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('food')
            and   name  = 'Relationship_3_FK'
            and   indid > 0
            and   indid < 255)
   drop index food.Relationship_3_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('food')
            and   name  = 'Relationship_1_FK'
            and   indid > 0
            and   indid < 255)
   drop index food.Relationship_1_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('food')
            and   type = 'U')
   drop table food
go

if exists (select 1
            from  sysobjects
           where  id = object_id('foodtype')
            and   type = 'U')
   drop table foodtype
go

if exists (select 1
            from  sysobjects
           where  id = object_id('homepage')
            and   type = 'U')
   drop table homepage
go

if exists (select 1
            from  sysobjects
           where  id = object_id('news')
            and   type = 'U')
   drop table news
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('orderdetail')
            and   name  = 'Relationship_5_FK'
            and   indid > 0
            and   indid < 255)
   drop index orderdetail.Relationship_5_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('orderdetail')
            and   name  = 'Relationship_4_FK'
            and   indid > 0
            and   indid < 255)
   drop index orderdetail.Relationship_4_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('orderdetail')
            and   type = 'U')
   drop table orderdetail
go

if exists (select 1
            from  sysobjects
           where  id = object_id('orders')
            and   type = 'U')
   drop table orders
go

if exists (select 1
            from  sysobjects
           where  id = object_id('picture')
            and   type = 'U')
   drop table picture
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('reservation')
            and   name  = 'Relationship_2_FK'
            and   indid > 0
            and   indid < 255)
   drop index reservation.Relationship_2_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('reservation')
            and   type = 'U')
   drop table reservation
go

if exists (select 1
            from  sysobjects
           where  id = object_id('users')
            and   type = 'U')
   drop table users
go

/*==============================================================*/
/* Table: food                                                  */
/*==============================================================*/
create table food (
   fid                  int                  identity,
   ftid                 int                  null,
   pid                  int                  null,
   fname                varchar(200)         not null,
   fdetial              varchar(1000)        not null,
   fprice               decimal(8,2)         not null,
   constraint PK_FOOD primary key nonclustered (fid)
)
go

/*==============================================================*/
/* Index: Relationship_1_FK                                     */
/*==============================================================*/
create index Relationship_1_FK on food (
ftid ASC
)
go

/*==============================================================*/
/* Index: Relationship_3_FK                                     */
/*==============================================================*/
create index Relationship_3_FK on food (
pid ASC
)
go

/*==============================================================*/
/* Table: foodtype                                              */
/*==============================================================*/
create table foodtype (
   ftid                 int                  identity,
   ftname               varchar(200)         not null,
   constraint PK_FOODTYPE primary key nonclustered (ftid)
)
go

/*==============================================================*/
/* Table: homepage                                              */
/*==============================================================*/
create table homepage (
   hid                  int                  identity,
   pic                  varchar(200)         null,
   title                varchar(200)         null,
   contents             varchar(2000)        null,
   type                 varchar(50)          null,
   constraint PK_HOMEPAGE primary key nonclustered (hid)
)
go

/*==============================================================*/
/* Table: news                                                  */
/*==============================================================*/
create table news (
   nid                  int                  identity,
   nauthor              varchar(200)         not null,
   ndate                datetime             not null,
   ncontent             varchar(8000)        not null,
   ntag                 varchar(200)         not null,
   constraint PK_NEWS primary key nonclustered (nid)
)
go

/*==============================================================*/
/* Table: orderdetail                                           */
/*==============================================================*/
create table orderdetail (
   oid                  int                  not null,
   fid                  int                  not null,
   odquant              int                  not null,
   constraint PK_ORDERDETAIL primary key (oid, fid)
)
go

/*==============================================================*/
/* Index: Relationship_4_FK                                     */
/*==============================================================*/
create index Relationship_4_FK on orderdetail (
fid ASC
)
go

/*==============================================================*/
/* Index: Relationship_5_FK                                     */
/*==============================================================*/
create index Relationship_5_FK on orderdetail (
oid ASC
)
go

/*==============================================================*/
/* Table: orders                                                */
/*==============================================================*/
create table orders (
   oid                  int                  identity,
   odate                datetime             not null,
   oquant               int                  not null,
   ofee                 decimal(8,2)         not null,
   ocheck               tinyint              not null default 0,
   constraint PK_ORDERS primary key nonclustered (oid)
)
go

/*==============================================================*/
/* Table: picture                                               */
/*==============================================================*/
create table picture (
   pid                  int                  identity,
   pname                varchar(200)         not null,
   ppath                varchar(200)         not null,
   pdisplay             tinyint              not null default 0,
   constraint PK_PICTURE primary key nonclustered (pid)
)
go

/*==============================================================*/
/* Table: reservation                                           */
/*==============================================================*/
create table reservation (
   resid                int                  identity,
   uid                  int                  null,
   restitle             varchar(200)         not null,
   resdate              datetime             not null,
   resinfo              varchar(2000)        not null,
   resseat              int                  not null,
   constraint PK_RESERVATION primary key nonclustered (resid)
)
go

/*==============================================================*/
/* Index: Relationship_2_FK                                     */
/*==============================================================*/
create index Relationship_2_FK on reservation (
uid ASC
)
go

/*==============================================================*/
/* Table: users                                                 */
/*==============================================================*/
create table users (
   uid                  int                  identity,
   uname                varchar(50)          not null,
   upassword            varchar(50)          null,
   utelphone            varchar(50)          null,
   utype                tinyint              not null default 3,
   ustaffinfo           varchar(2000)        null,
   ustaffdisplay        tinyint              not null default 0,
   upic                 varchar(200)         null,
   constraint PK_USERS primary key nonclustered (uid)
)
go

alter table food
   add constraint FK_FOOD_RELATIONS_FOODTYPE foreign key (ftid)
      references foodtype (ftid)
go

alter table food
   add constraint FK_FOOD_RELATIONS_PICTURE foreign key (pid)
      references picture (pid)
go

alter table orderdetail
   add constraint FK_ORDERDET_RELATIONS_FOOD foreign key (fid)
      references food (fid)
go

alter table orderdetail
   add constraint FK_ORDERDET_RELATIONS_ORDERS foreign key (oid)
      references orders (oid)
go

alter table reservation
   add constraint FK_RESERVAT_RELATIONS_USERS foreign key (uid)
      references users (uid)
go

