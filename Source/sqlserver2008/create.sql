if not exists(select * from sys.databases where name = 'angelabible')
    create database angelabible

go


use angelabible;

if exists (select 1
            from  sysobjects
           where  id = object_id('"Key"')
            and   type = 'U')
   drop table "Key"
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Note')
            and   type = 'U')
   drop table Note
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Text')
            and   type = 'U')
   drop table Text
go

if exists (select 1
            from  sysobjects
           where  id = object_id('UserGroup')
            and   type = 'U')
   drop table UserGroup
go

if exists (select 1
            from  sysobjects
           where  id = object_id('UserInfo')
            and   type = 'U')
   drop table UserInfo
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
   book                 char(32)             not null,
   chapter              int                  not null,
   verse                int                  not null,
   constraint PK_KEY primary key nonclustered (osisID),
   constraint AK_UK_BOOK_CHAPTER_VE_KEY unique (book, chapter, verse)
)
go

/*==============================================================*/
/* Table: Note                                                  */
/*==============================================================*/
create table Note (
   osisID               char(32)             not null,
   username             char(32)             not null,
   Content              char(1024)           not null,
   timestamp            datetime             not null,
   constraint AK_PK_NOTE unique (osisID, username)
)
go

/*==============================================================*/
/* Table: Text                                                  */
/*==============================================================*/
create table Text (
   osisID               char(32)             not null,
   initial              char(32)             not null,
   Text                 char(1024)           null,
   constraint PK_TEXT primary key nonclustered (osisID, initial)
)
go

/*==============================================================*/
/* Table: UserGroup                                             */
/*==============================================================*/
create table UserGroup (
   groupId              int                  not null,
   groupName            char(32)             not null,
   constraint PK_USERGROUP primary key nonclustered (groupId)
)
go

/*==============================================================*/
/* Table: UserInfo                                              */
/*==============================================================*/
create table UserInfo (
   username             char(32)             not null,
   groupId              int                  not null,
   password             char(256)            not null,
   email                char(256)            not null,
   constraint PK_USERINFO primary key nonclustered (username)
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
   constraint PK_VERSION primary key nonclustered (initial),
   constraint AK_UK_OSISREF_VERSION unique (osisRef)
)
go
