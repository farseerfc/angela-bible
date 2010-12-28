use angelabible;

insert into [UserGroup](groupId,groupName) values(0,'管理员');
insert into [UserGroup](groupId,groupName) values(1,'基督徒');
insert into [UserGroup](groupId,groupName) values(2,'了解中');
insert into [UserGroup](groupId,groupName) values(3,'非基督徒');

go

insert into [UserInfo]([groupId],[username],[password],[email]) select 0,'farseer','angela','farseerfc@gmail.com' where not exists(select * from [dbo].[UserInfo] where userName = 'farseer');
insert into [UserInfo]([groupId],[username],[password],[email]) select 0,'angela','farseer','angela.toughitout@gmail.com' where not exists(select * from [dbo].[UserInfo] where userName = 'angela');
insert into [UserInfo]([groupId],[username],[password],[email]) select 3,'guest','','guest@example.com' where not exists(select * from [dbo].[UserInfo] where userName = 'guest');

go

insert into [language]([language],[name]) values('English','英语');
insert into [language]([language],[name]) values('Chinese','汉语');
insert into [language]([language],[name]) values('Hebrew','希伯来语');
insert into [language]([language],[name]) values('German','德语');

go