package com.discExchange.service;

import com.discExchange.dao.DiscDAO;
import com.discExchange.dao.UserDAO;
import com.discExchange.model.DiscEntity;
import com.discExchange.model.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by adMin on 10.08.2017.
 */
@Service
public class DiscServiceImpl implements DiscService {

    private DiscDAO discDAO;
    //private UserDAO userDAO;

    public void setDiscDAO(DiscDAO discDAO) {
        this.discDAO = discDAO;
    }

    /*public void setUserDAO(UserDAO userDAO) { this.userDAO = userDAO; }*/



    @Override
    @Transactional
    public List<DiscEntity> listDiscs() {
        return this.discDAO.listDiscs();
    }

    @Override
    @Transactional
    public DiscEntity addNewDisc(String discName, String authorName) {
        DiscEntity newDisc = new DiscEntity();
        newDisc.setName(discName);
        newDisc.setAuthor(authorName);


        DiscEntity result = this.discDAO.addDisc(newDisc);
        return result;
    }
}
