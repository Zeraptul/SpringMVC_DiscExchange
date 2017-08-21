package com.discExchange.service;

import com.discExchange.dao.DiscDAO;
import com.discExchange.dao.UserDAO;
import com.discExchange.model.DiscEntity;
import com.discExchange.model.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DiscService {

	List<DiscEntity> listDiscs();

    DiscEntity addNewDisc(String discName, String authorName);
}


