package com.discExchange.dao;

import com.discExchange.model.DiscEntity;
import com.discExchange.model.TakenDiscEntity;
import com.discExchange.model.UserEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TakenDiscDAOImpl implements TakenDiscDAO {

    private static final Logger logger = LoggerFactory.getLogger(TakenDiscDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) { this.sessionFactory = sf;}

    @SuppressWarnings("unchecked")
    @Override
    public List<TakenDiscEntity> listAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from TakenDiscEntity");
        List<TakenDiscEntity> result = query.list();
        return result;

    }

    @Override
    public TakenDiscEntity addDisc(UserEntity ownerUser, DiscEntity disc, UserEntity takerUser, String description) {
        Session session = this.sessionFactory.getCurrentSession();
        TakenDiscEntity takenDiscEntity = new TakenDiscEntity();
        takenDiscEntity.setOwnerUser(ownerUser);
        takenDiscEntity.setDisc(disc);
        takenDiscEntity.setTakerUser(takerUser);
        takenDiscEntity.setDescription(description);
        session.persist(takenDiscEntity);
        logger.info("taken saved successfully, TakenDisc Details="+takenDiscEntity);
        return takenDiscEntity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TakenDiscEntity> listAllFree() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from TakenDiscEntity t where t.takerUser is null");
        List<TakenDiscEntity> result = query.list();
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TakenDiscEntity> listAllUserTaken(UserEntity takerUser) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from TakenDiscEntity t where t.takerUser = :takerUser");
        query.setParameter("takerUser", takerUser);
        List<TakenDiscEntity> result = query.list();
        return result;

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TakenDiscEntity> listAllTakenFromUser(UserEntity ownerUser) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from TakenDiscEntity t where t.ownerUser = :ownerUser and t.takerUser is not null");
        query.setParameter("ownerUser", ownerUser);
        List<TakenDiscEntity> result = query.list();
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean returnDiscById(int discId) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("update TakenDiscEntity t set t.takerUser = :takerUser where t.id = :discId");
        query.setParameter("takerUser", null);
        query.setParameter("discId", discId);
        int updateRowsCount = query.executeUpdate();
        return updateRowsCount > 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean takeDiscByIdToUser(int takenDiscId, int takerUserId) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("update TakenDiscEntity t set t.takerUser.id = :takerUserId where t.id = :discId");
        query.setParameter("discId", takenDiscId);
        query.setParameter("takerUserId", takerUserId);

        int updateRowsCount = query.executeUpdate();
        return updateRowsCount > 0;
    }
}
