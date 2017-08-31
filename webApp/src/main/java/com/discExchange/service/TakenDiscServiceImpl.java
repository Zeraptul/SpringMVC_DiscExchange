package com.discExchange.service;

import com.discExchange.dao.TakenDiscDAO;
import com.discExchange.model.DiscEntity;
import com.discExchange.model.TakenDiscEntity;
import com.discExchange.model.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TakenDiscServiceImpl implements TakenDiscService {

    private TakenDiscDAO takenDiscDAO;
    public void setTakenDiscDAO(TakenDiscDAO takenDiscDAO) {
        this.takenDiscDAO = takenDiscDAO;
    }

    @Transactional
    @Override
    public List<TakenDiscEntity> listAll() {
        return takenDiscDAO.listAll();
    }

    @Transactional
    @Override
    public TakenDiscEntity addDisc(UserEntity ownerUser, DiscEntity disc, UserEntity takerUser, String description) {
        return takenDiscDAO.addDisc(ownerUser, disc, takerUser, description);
    }

    @Transactional
    @Override
    public List<TakenDiscEntity> listAllFree() {
        return takenDiscDAO.listAllFree();
    }

    @Transactional
    @Override
    public List<TakenDiscEntity> listAllUserTaken(UserEntity takerUser) {
        return takenDiscDAO.listAllUserTaken(takerUser);
    }

    @Transactional
    @Override
    public List<TakenDiscEntity> listAllTakenFromUser(UserEntity ownerUser) {
        return takenDiscDAO.listAllTakenFromUser(ownerUser);
    }

    @Transactional
    @Override
    public void ReturnTakenDiscById(int discId) {
        takenDiscDAO.returnDiscById(discId);
    }

    @Transactional
    @Override
    public void takeDiscByIdAndUser(int takenDiscId, int takerUserId) {
        takenDiscDAO.takeDiscByIdToUser(takenDiscId, takerUserId);
    }
}
