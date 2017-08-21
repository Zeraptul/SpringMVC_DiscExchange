INSERT INTO Discs (id, name, author) VALUES (1, N'disc2', N'My')
INSERT INTO Discs (id, name, author) VALUES (2, N'disc1', N'My')
INSERT INTO Discs (id, name, author) VALUES (4, N'disc_5', N'Not')
INSERT INTO Discs (id, name, author) VALUES (14, N'my disc', N'ohter')
INSERT INTO Discs (id, name, author) VALUES (15, N'adfa', N'sdfs')
INSERT INTO Discs (id, name, author) VALUES (16, N'adf', N'adfasdf')
INSERT INTO Discs (id, name, author) VALUES (17, N'user4', N'adfasdf')


INSERT INTO TakenDiscs (id, ownerUserId, discId, takingUserId, description) VALUES (1, 1, 1, 3, N'Borrowed 3 days')
INSERT INTO TakenDiscs (id, ownerUserId, discId, takingUserId, description) VALUES (2, 1, 2, 4, N'descr')
INSERT INTO TakenDiscs (id, ownerUserId, discId, takingUserId, description) VALUES (8, 3, 4, 1, N'daf')
INSERT INTO TakenDiscs (id, ownerUserId, discId, takingUserId, description) VALUES (4, 1, 14, NULL, N'not taken')
INSERT INTO TakenDiscs (id, ownerUserId, discId, takingUserId, description) VALUES (5, 1, 15, NULL, N'not taken')
INSERT INTO TakenDiscs (id, ownerUserId, discId, takingUserId, description) VALUES (9, 1, 16, NULL, N'not taken')
INSERT INTO TakenDiscs (id, ownerUserId, discId, takingUserId, description) VALUES (10, 4, 17, NULL, N'not taken')


--INSERT INTO Users (id, nickname, password) VALUES (1, N'gleb', N'$2a$12$nj3aLisLlyrZcGp5SGaE/OqI.FYKCfzUI8kyp8BVK3rzNCO3vCL3e')
INSERT INTO Users (id, nickname, password) VALUES (1, N'test', N'$2a$12$wmSqgua9CDyFzD8MqR7SUOGBzm9.2umANqbVQXkB8NgPIpJ7M5Fr6')
INSERT INTO Users (id, nickname, password) VALUES (3, N'user2', N'$2a$12$IrJG9kBjpiG1Vux9JxbVWeb09liRCXvxyBwf6v8Ewkr.qQZCYvC9W')
INSERT INTO Users (id, nickname, password) VALUES (4, N'user4', N'$2a$12$GbHZ7b2so3xs2Rm6X4Il0Osevj17Cm.tZa4XL7WybJiekolFqQyk.')

