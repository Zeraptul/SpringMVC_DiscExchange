package com.discExchange.dao;

import com.discExchange.model.DiscEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DiscDAOImpl implements DiscDAO {

	private static final Logger logger = LoggerFactory.getLogger(DiscDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiscEntity> listDiscs() {
		Session session = this.sessionFactory.getCurrentSession();
		List<DiscEntity> discList = session.createQuery("from DiscEntity").list();
		for (DiscEntity discEntity : discList) {
			logger.info("Disc List::" + discEntity);
		}
		return discList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiscEntity> listDiscsOfUser(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from DiscEntity where ownerId = :ownerId");
		query.setParameter("ownerId", userId);
		List<DiscEntity> discList = query.list();
		for (DiscEntity discEntity : discList) {
			logger.info("Disc List::" + discEntity);
		}
		return discList;
	}

    @Override
    public DiscEntity addDisc(DiscEntity newDisc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(newDisc);
		logger.info("Disc saved successfully, Disc details="+newDisc);
        return newDisc;
    }
}
