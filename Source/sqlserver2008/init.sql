use angelabible;

insert into [UserGroup](groupId,groupName) values(0,'����Ա');
insert into [UserGroup](groupId,groupName) values(1,'����ͽ');
insert into [UserGroup](groupId,groupName) values(2,'�˽���');
insert into [UserGroup](groupId,groupName) values(3,'�ǻ���ͽ');

go

insert into [UserInfo]([groupId],[username],[password],[email]) select 0,'farseer','angela','farseerfc@gmail.com' where not exists(select * from [dbo].[UserInfo] where userName = 'farseer');
insert into [UserInfo]([groupId],[username],[password],[email]) select 0,'angela','farseer','angela.toughitout@gmail.com' where not exists(select * from [dbo].[UserInfo] where userName = 'angela');
insert into [UserInfo]([groupId],[username],[password],[email]) select 3,'guest','','guest@example.com' where not exists(select * from [dbo].[UserInfo] where userName = 'guest');

go

insert into [language]([language],[name]) values('English','Ӣ��');
insert into [language]([language],[name]) values('Chinese','����');
insert into [language]([language],[name]) values('Hebrew','ϣ������');
insert into [language]([language],[name]) values('German','����');

go