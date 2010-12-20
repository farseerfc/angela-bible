drop database angelabible;
create database angelabible;

go


use angelabible;

/* hahaha */
if exists (select 1
            from  sysobjects
           where  id = object_id('"Key"')
            and   type = 'U')
   drop table "Key"
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Text')
            and   type = 'U')
   drop table Text
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Version')
            and   type = 'U')
   drop table Version
go

/*==============================================================*/
/* Table: "Key"                                                 */
/*==============================================================*/
create table "Key" (
   osisID               char(32)             not null,
   book                 char(32)             null,
   chapter              int                  null,
   verse                int                  null,
   constraint PK_KEY primary key nonclustered (osisID)
)
go

/*==============================================================*/
/* Table: Text                                                  */
/*==============================================================*/
create table Text (
   osisID               char(32)             not null,
   initial              char(32)             not null,
   text                 char(1024)           null,
   constraint PK_TEXT primary key nonclustered (osisID, initial)
)
go

/*==============================================================*/
/* Table: Version                                               */
/*==============================================================*/
create table Version (
   initial              char(32)             not null,
   osisRef              char(32)             not null,
   title                char(256)            null,
   language             char(32)             null,
   constraint PK_VERSION primary key nonclustered (initial)
)
go
