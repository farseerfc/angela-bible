if exists(select * from sys.databases where name = 'angelabible')
	drop database angelabible;
go
create database angelabible;
go


use angelabible;

if exists (select 1
          from sysobjects
          where id = object_id('InsertSuggestion')
          and type = 'TR')
   drop trigger InsertSuggestion
go

if exists (select 1
          from sysobjects
          where  id = object_id('ChapterCountOfVersion')
          and type = 'P')
   drop procedure ChapterCountOfVersion
go

if exists (select 1
          from sysobjects
          where  id = object_id('EmailCheck')
          and type in ('IF', 'FN', 'TF'))
   drop function EmailCheck
go

if exists (select 1
          from sysobjects
          where  id = object_id('InsertText')
          and type = 'P')
   drop procedure InsertText
go

if exists (select 1
          from sysobjects
          where  id = object_id('VerseCountOfVersion')
          and type = 'P')
   drop procedure VerseCountOfVersion
go

if exists (select 1
   from dbo.sysreferences r join dbo.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('"Key"') and o.name = 'FK_KEY_RELATIONS_BOOK')
alter table "Key"
   drop constraint FK_KEY_RELATIONS_BOOK
go

if exists (select 1
   from dbo.sysreferences r join dbo.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Note') and o.name = 'FK_NOTE_RELATIONS_KEY')
alter table Note
   drop constraint FK_NOTE_RELATIONS_KEY
go

if exists (select 1
   from dbo.sysreferences r join dbo.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Note') and o.name = 'FK_NOTE_RELATIONS_USERINFO')
alter table Note
   drop constraint FK_NOTE_RELATIONS_USERINFO
go

if exists (select 1
   from dbo.sysreferences r join dbo.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Suggestion') and o.name = 'FK_SUGGESTI_RELATIONS_USERINFO')
alter table Suggestion
   drop constraint FK_SUGGESTI_RELATIONS_USERINFO
go

if exists (select 1
   from dbo.sysreferences r join dbo.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Text') and o.name = 'FK_TEXT_OFKEY_KEY')
alter table Text
   drop constraint FK_TEXT_OFKEY_KEY
go

if exists (select 1
   from dbo.sysreferences r join dbo.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Text') and o.name = 'FK_TEXT_OFVERSION_VERSION')
alter table Text
   drop constraint FK_TEXT_OFVERSION_VERSION
go

if exists (select 1
   from dbo.sysreferences r join dbo.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('UserInfo') and o.name = 'FK_USERINFO_RELATIONS_USERGROU')
alter table UserInfo
   drop constraint FK_USERINFO_RELATIONS_USERGROU
go

if exists (select 1
   from dbo.sysreferences r join dbo.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Version') and o.name = 'FK_VERSION_RELATIONS_LANGUAGE')
alter table Version
   drop constraint FK_VERSION_RELATIONS_LANGUAGE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('BookGroup')
            and   type = 'V')
   drop view BookGroup
go

if exists (select 1
            from  sysobjects
           where  id = object_id('BookVersion')
            and   type = 'V')
   drop view BookVersion
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Book')
            and   type = 'U')
   drop table Book
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Carol')
            and   type = 'U')
   drop table Carol
go

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
            from  sysindexes
           where  id    = object_id('Suggestion')
            and   name  = 'Relationship_8_FK'
            and   indid > 0
            and   indid < 255)
   drop index Suggestion.Relationship_8_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Suggestion')
            and   type = 'U')
   drop table Suggestion
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

if exists (select 1
            from  sysobjects
           where  id = object_id('language')
            and   type = 'U')
   drop table language
go


create function EmailCheck (@email varChar(256))
RETURNS bit
AS
BEGIN
DECLARE 
    @invalChars varchar(5),
    @valid int,
    @badChar varchar(1),
    @atPos int,
    @periodPos int
SET @valid = 1
SET @invalChars = ' /:,;'
--Check to see if it's blank
IF len(ltrim(rtrim(@email))) = 0
   SET @valid = 0
ELSE
        --Loop invalid characters to see if it exists in email
      WHILE len(@invalChars) > 0
         BEGIN
            SET @badChar = substring(@invalChars,1,1)
            IF(charindex(@badChar,@email) > 0)
               --If invalid character was found, return 0 to invalidate
               SET @valid = 0
            SET @invalChars = replace(@invalChars,@badChar,'')
         END
      --Check to see if "@" exists.
      SET @atPos = charindex('@',@email,1)
      IF @atPos = 0
         SET @valid = 0
       --Check to see if extra "@" exists after 1st "@".
      IF charindex('@',@email,@atPos+1) > 0
         SET @valid = 0
      SET @periodPos = charindex('.',@email,@atPos)
      IF @periodPos = 0
         SET @valid = 0
      IF (@periodPos+3) > len(@email)
         SET @valid = 0
      RETURN (@valid)
END
go



/*==============================================================*/
/* Table: Book                                                  */
/*==============================================================*/
create table Book (
   book                 char(32)             not null,
   "index"              int                  not null,
   name                 char(256)            not null,
   describe             char(1024)           not null,
   testament            char(2)              not null,
   "group"              char(32)             not null,
   constraint PK_BOOK primary key nonclustered (book),
   constraint AK_AK_INDEX_BOOK unique ("index")
)
go

/*==============================================================*/
/* Table: Carol                                                 */
/*==============================================================*/
create table Carol (
   title                char(32)             not null,
   describe             char(1024)           null,
   lyric                char(1024)           null,
   filepath             char(1024)           null,
   constraint PK_CAROL primary key nonclustered (title)
)
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
   constraint AK_UK_BOOK_CHAPTER_VE_KEY unique (chapter, verse, book)
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
   constraint PK_NOTE primary key nonclustered (osisID, username)
)
go

/*==============================================================*/
/* Table: Suggestion                                            */
/*==============================================================*/
create table Suggestion (
   username             char(32)             not null,
   suggestion           char(1024)           null,
   timestamp            datetime             not null,
   constraint PK_SUGGESTION primary key nonclustered (username, timestamp)
)
go

/*==============================================================*/
/* Index: Relationship_8_FK                                     */
/*==============================================================*/
create index Relationship_8_FK on Suggestion (
username ASC
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
   email                char(256)            not null
      constraint CKC_EMAIL_USERINFO check (dbo.EmailCheck(LTRIM(RTRIM(email))) =1),
   constraint PK_USERINFO primary key nonclustered (username)
)
go

/*==============================================================*/
/* Table: Version                                               */
/*==============================================================*/
create table Version (
   initial              char(32)             not null,
   language             char(32)             not null,
   osisRef              char(32)             not null,
   title                char(256)            null,
   describe             char(1024)           null,
   constraint PK_VERSION primary key nonclustered (initial),
   constraint AK_UK_OSISREF_VERSION unique (osisRef)
)
go

/*==============================================================*/
/* Table: language                                              */
/*==============================================================*/
create table language (
   language             char(32)             not null,
   name                 char(256)            not null,
   constraint PK_LANGUAGE primary key nonclustered (language)
)
go

/*==============================================================*/
/* View: BookGroup                                              */
/*==============================================================*/
create view BookGroup as
select [group],MIN([index])as [mindex] ,
    MIN([testament]) as [testament]
	from [book] group by [group] ;
go

/*==============================================================*/
/* View: BookVersion                                            */
/*==============================================================*/
create view BookVersion as
select [Key].[book]as book ,[Text].[initial] as initial from (
	[Text] join [Version] on [Version].initial=[Text].initial)
	 join [Key] on [Key].osisID=[Text].osisId 
	 group by [Key].[book],[Text].[initial]
go

alter table "Key"
   add constraint FK_KEY_RELATIONS_BOOK foreign key (book)
      references Book (book)
         on update cascade on delete cascade
go

alter table Note
   add constraint FK_NOTE_RELATIONS_KEY foreign key (osisID)
      references "Key" (osisID)
         on update cascade on delete cascade
go

alter table Note
   add constraint FK_NOTE_RELATIONS_USERINFO foreign key (username)
      references UserInfo (username)
         on update cascade on delete cascade
go

alter table Suggestion
   add constraint FK_SUGGESTI_RELATIONS_USERINFO foreign key (username)
      references UserInfo (username)
         on update cascade on delete cascade
go

alter table Text
   add constraint FK_TEXT_OFKEY_KEY foreign key (osisID)
      references "Key" (osisID)
         on update cascade on delete cascade
go

alter table Text
   add constraint FK_TEXT_OFVERSION_VERSION foreign key (initial)
      references Version (initial)
         on update cascade on delete cascade
go

alter table UserInfo
   add constraint FK_USERINFO_RELATIONS_USERGROU foreign key (groupId)
      references UserGroup (groupId)
         on update cascade on delete cascade
go

alter table Version
   add constraint FK_VERSION_RELATIONS_LANGUAGE foreign key (language)
      references language (language)
         on update cascade on delete cascade
go


create procedure ChapterCountOfVersion
    @book char(32),
    @initial char(32)
as
select Max(chapter) from ( 
    [Text] join [Key] on [Text].osisID=[Key].osisID) 
    join [Version] on [Version].initial=[Text].initial 
        where book=@book and [Text].initial=@initial
go



create procedure InsertText
	@chapter INTEGER,
	@verse INTEGER,
	@book CHAR(32),
	@osisId CHAR(32),
	@initial CHAR(32),
	@text CHAR(1024)
as
begin
update [dbo].[key] set [book]=@book,[chapter]=@chapter,[verse]=@verse where [osisId]=@osisId;
if @@ROWCOUNT=0 
	insert into [dbo].[key](osisId,book,chapter,verse) values (@osisId,@book,@chapter,@verse);
update [dbo].[Text] set text=@text where initial=@initial and osisId=@osisId;
if @@ROWCOUNT=0
	insert into [dbo].[Text](initial,osisId,text) values(@initial,@osisId,@text);
end
go


create procedure VerseCountOfVersion
    @book char(32),
    @chapter integer,
    @initial char(32)
as
select Max(verse) from ( 
    [Text] join [Key] on [Text].osisID=[Key].osisID) 
    join [Version] on [Version].initial=[Text].initial 
        where book=@book and chapter=@chapter and [Text].initial=@initial
go


create trigger InsertSuggestion on UserInfo 
for insert as
begin
    declare
       @username char(32),
       @suggestion char(1024);
     select @username = i.username from inserted i ;
     set @suggestion = '新用户 '+RTRIM(LTRIM(@username))+' 于 '+CONVERT(char(30), CURRENT_TIMESTAMP)+'注册';
     insert Suggestion(username,suggestion,timestamp) values(@username,@suggestion,CURRENT_TIMESTAMP);
end
go
