alter table TakenDiscs
  add constraint FK_TakenDiscs_Discs
foreign key (discId) references Discs
go

alter table TakenDiscs
  add constraint FK_TakenDiscs_OwnerUsers
foreign key (ownerUserId) references Users
go

alter table TakenDiscs
  add constraint FK_TakenDiscs_TakerUsers
foreign key (takingUserId) references Users
go

