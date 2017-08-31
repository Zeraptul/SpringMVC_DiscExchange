--DROP DATABASE [testDb]
--CREATE DATABASE [testDb]
--GO
/****** Object:  Table [dbo].[Discs]    Script Date: 11.08.2017 17:52:00 ******/


CREATE TABLE users (
  id         INTEGER PRIMARY KEY,
  nickname VARCHAR(50) Not Null,
  password  VARCHAR(100) not null
);

CREATE TABLE discs(
  id  INTEGER IDENTITY PRIMARY KEY,
  name nvarchar(50) NOT NULL,
  author nvarchar(50) NOT NULL
);

CREATE TABLE TakenDiscs(
  id INTEGER IDENTITY NOT NULL,
  ownerUserId INTEGER NOT NULL,
  discId INTEGER NOT NULL,
  takingUserId INTEGER NULL,
  description nvarchar(50) NOT NULL
);

alter table TakenDiscs
  add constraint FK_TakenDiscs_Discs
foreign key (discId) references Discs;


alter table TakenDiscs
  add constraint FK_TakenDiscs_OwnerUsers
foreign key (ownerUserId) references Users;

alter table TakenDiscs
  add constraint FK_TakenDiscs_TakerUsers
foreign key (takingUserId) references Users;



