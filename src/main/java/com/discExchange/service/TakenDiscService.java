package com.discExchange.service;

import com.discExchange.model.DiscEntity;
import com.discExchange.model.TakenDiscEntity;
import com.discExchange.model.UserEntity;

import java.util.List;

/**
 * Created by adMin on 10.08.2017.
 */
public interface TakenDiscService {
    List<TakenDiscEntity> listAll();

    TakenDiscEntity addDisc(UserEntity ownerUser, DiscEntity disc, UserEntity takerUser, String description);

    List<TakenDiscEntity> listAllFree();

    List<TakenDiscEntity> listAllUserTaken(UserEntity takerUser);

    List<TakenDiscEntity> listAllTakenFromUser(UserEntity ownerUser);

    void ReturnTakenDiscById(int takenDiscId);

    void takeDiscByIdAndUser(int takenDiscId, int takerUserId);
}

