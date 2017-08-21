package com.discExchange.dao;

import com.discExchange.model.DiscEntity;

import java.util.List;

public interface DiscDAO {

	List<DiscEntity> listDiscs();

	List<DiscEntity> listDiscsOfUser(int userId);

    DiscEntity addDisc(DiscEntity newDisc);
}

