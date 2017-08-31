package com.discExchange.dao;

import com.discExchange.model.DiscEntity;
import com.discExchange.model.TakenDiscEntity;
import com.discExchange.model.UserEntity;

import java.util.List;

/**
 * Created by adMin on 10.08.2017.
 */
public interface TakenDiscDAO {
    List<TakenDiscEntity> listAll();

    TakenDiscEntity addDisc(UserEntity ownerUser, DiscEntity disc, UserEntity takerUser, String description);

    List<TakenDiscEntity> listAllFree();

    List<TakenDiscEntity> listAllUserTaken(UserEntity takerUser);

    List<TakenDiscEntity> listAllTakenFromUser(UserEntity ownerUser);

    boolean returnDiscById(int discId);

    boolean takeDiscByIdToUser(int takenDiscId, int takerUserId);
}

