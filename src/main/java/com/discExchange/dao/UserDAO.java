package com.discExchange.dao;

import com.discExchange.model.DiscEntity;
import com.discExchange.model.UserEntity;

public interface UserDAO {


	UserEntity findUserByNamePassword(String nickname, String password);

    UserEntity getUserById(int userId);
}

