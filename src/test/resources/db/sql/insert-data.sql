
INSERT INTO Users (id, nickname, password) VALUES (1, N'test', N'$2a$12$wmSqgua9CDyFzD8MqR7SUOGBzm9.2umANqbVQXkB8NgPIpJ7M5Fr6');
INSERT INTO Users (id, nickname, password) VALUES (3, N'user2', N'$2a$12$IrJG9kBjpiG1Vux9JxbVWeb09liRCXvxyBwf6v8Ewkr.qQZCYvC9W');
INSERT INTO Users (id, nickname, password) VALUES (4, N'user4', N'$2a$12$GbHZ7b2so3xs2Rm6X4Il0Osevj17Cm.tZa4XL7WybJiekolFqQyk.');
INSERT INTO Users (id, nickname, password) VALUES (5, N'user6', N'incorrectHashFormatOumGAenUubtD7eJRDfi.3nYrwY1PdgXbTshCUZZC');


INSERT INTO Discs (id, name, author) VALUES (1, N'disc2', N'My');
INSERT INTO Discs (id, name, author) VALUES (2, N'disc1', N'My');
INSERT INTO Discs (id, name, author) VALUES (4, N'disc_5', N'Not');

INSERT INTO TakenDiscs (id, ownerUserId, discId, takingUserId, description) VALUES (1, 1, 1, 3, N'Borrowed 3 days');
INSERT INTO TakenDiscs (id, ownerUserId, discId, takingUserId, description) VALUES (2, 1, 2, 4, N'descr')
INSERT INTO TakenDiscs (id, ownerUserId, discId, takingUserId, description) VALUES (4, 1, 4, NULL, N'not taken')

INSERT INTO Authors(id, name) VALUES (1, N'Green');
INSERT INTO Authors(id, name) VALUES (2, N'Blue');

INSERT Into Books(id, title) VALUES (1, N'myBook');
INSERT Into Books(id, title) VALUES (2, N'secBook');

INSERT INTO AuthorBookLinks(id, authorId, bookId, description) VALUES (1, 1, 1, N'myDescr');
INSERT INTO AuthorBookLinks(id, authorId, bookId, description) VALUES (2, 2, 1, N'myDescr');
INSERT INTO AuthorBookLinks(id, authorId, bookId, description) VALUES (3, 2, 2, N'myDescr');