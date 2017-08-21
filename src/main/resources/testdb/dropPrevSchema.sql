--USE [testDb];

ALTER TABLE TakenDiscs DROP CONSTRAINT FK_TakenDiscs_TakerUsers;
ALTER TABLE TakenDiscs DROP CONSTRAINT FK_TakenDiscs_OwnerUsers;
ALTER TABLE TakenDiscs DROP CONSTRAINT FK_TakenDiscs_Discs;

DROP TABLE Users;
DROP TABLE TakenDiscs;
DROP TABLE Discs;




