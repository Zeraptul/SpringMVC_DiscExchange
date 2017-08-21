package com.discExchange.service;

import com.discExchange.model.UserEntity;

public interface UserService {

	public UserEntity findUserByNamePassword(String nickname, String password);

    UserEntity getUserById(int ownerUserId);
}

