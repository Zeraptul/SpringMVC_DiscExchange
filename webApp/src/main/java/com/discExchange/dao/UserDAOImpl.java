package com.discExchange.dao;


import com.discExchange.model.UserEntity;
import com.discExchange.utility.PasswordUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}


	@SuppressWarnings("unchecked")
	@Override
	public UserEntity findUserByNamePassword(String nickname, String password) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from UserEntity where nickname = :nickname");
		//session.createQuery("from User where nickname = :nickname and password = :password");

		query.setParameter("nickname", nickname );
		//query.setParameter("password", password );
		List<UserEntity> list = query.list();
		if (list == null || list.isEmpty())
			return null;

		UserEntity user = (UserEntity) list.get(0);
		try {
			boolean isCorrect = PasswordUtils.checkPassword(password, user.getPassword());
			UserEntity result = isCorrect ? user : null;
			return result;
		}
		catch (IllegalArgumentException exc)
		{
			logger.error("invalid passwordHash", exc);
			return null;
		}

	}

    @Override
    public UserEntity getUserById(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		//get vs load - https://www.mkyong.com/hibernate/different-between-session-get-and-session-load/
		UserEntity result = (UserEntity) session.get(UserEntity.class, userId);
		if (result != null)
			logger.info("User loaded successfully, User details="+result);
		return result;
    }
}
