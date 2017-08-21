--DROP DATABASE [testDb]
--CREATE DATABASE [testDb]

--USE [testDb];

create table Discs
(
  id int identity
    constraint PK_Discs
    primary key,
  name nvarchar(50) not null,
  author nvarchar(50) not null
)
go

create table Person
(
  id int identity(2, 1),
  name nvarchar(50) not null,
  country nvarchar(50) not null
)
go

create table TakenDiscs
(
  id int identity(2, 1)
    constraint PK_TakenDiscs
    primary key,
  ownerUserId int not null,
  discId int not null,
  takingUserId int,
  description nvarchar(50) not null
)
go

create table Users
(
  id int identity
    constraint PK_Users
    primary key,
  nickname nvarchar(50) not null,
  password nvarchar(100) not null
)
go



