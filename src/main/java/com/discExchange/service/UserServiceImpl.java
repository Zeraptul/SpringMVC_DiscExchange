package com.discExchange.service;

import com.discExchange.dao.UserDAO;
import com.discExchange.model.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public UserEntity findUserByNamePassword(String nickname, String password)
	{
		return this.userDAO.findUserByNamePassword(nickname, password);
	}

	@Transactional
	@Override
	public UserEntity getUserById(int ownerUserId) {
		return this.userDAO.getUserById(ownerUserId);
	}

}
