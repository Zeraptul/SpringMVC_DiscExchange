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
)

/*CREATE TABLE Users(
  id int IDENTITY(1,1) NOT NULL,
  nickname nvarchar(50) NOT NULL,
  password nvarchar(100) NOT NULL
   -- WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
);*/

