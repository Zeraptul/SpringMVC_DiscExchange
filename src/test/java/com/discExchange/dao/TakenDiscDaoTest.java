package com.discExchange.dao;

import com.discExchange.dao.TakenDiscDAO;
import com.discExchange.model.DiscEntity;
import com.discExchange.model.TakenDiscEntity;
import com.discExchange.model.UserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by adMin on 13.08.2017.
 */
@ContextConfiguration("classpath:spring/servlet-context-test_2.xml")
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class TakenDiscDaoTest {

    @Autowired
    @Qualifier("takenDiscDAO")
    TakenDiscDAO takenDiscDAO;

    @Transactional
    @Test
    public void listAll_hasMany_success()    {
       List<TakenDiscEntity> all = takenDiscDAO.listAll();
       Assert.assertTrue(all.size() > 0);

       TakenDiscEntity first = all.get(0);
       Assert.assertEquals(1, first.getId());
       Assert.assertEquals(1, first.getOwnerUser().getId());
       Assert.assertEquals(1, first.getDisc().getId());
       Assert.assertEquals(3, first.getTakerUser().getId());
    }

    @Transactional
    @Test
    public void listAllFree_hasAny_success()    {
        List<TakenDiscEntity> allFree = takenDiscDAO.listAllFree();
        Assert.assertTrue(allFree.size() > 0);

        TakenDiscEntity first = allFree.get(0);
        Assert.assertNull( first.getTakerUser());
        //Assert.assertEquals(4, first.getId());
        Assert.assertEquals(1, first.getOwnerUser().getId());
        Assert.assertEquals(4, first.getDisc().getId());

    }

    @Transactional
    @Test
    public void listAllTakenFrom_hasMany_success()    {
        final int ownerId = 1;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(ownerId);

        List<TakenDiscEntity> allTakenFrom = takenDiscDAO.listAllTakenFromUser(userEntity);

        Assert.assertTrue(allTakenFrom.size() > 0);

        TakenDiscEntity first = allTakenFrom.get(0);
        Assert.assertEquals(ownerId, first.getOwnerUser().getId());
        Assert.assertNotNull( first.getTakerUser());
    }

    @Transactional
    @Test
    public void listAllUser_hasNoOne_success()    {
        final int takerId = 1;
        UserEntity takerUser = new UserEntity();
        takerUser.setId(takerId);

        List<TakenDiscEntity> allUserTaken = takenDiscDAO.listAllUserTaken(takerUser);

        Assert.assertEquals(0, allUserTaken.size());
    }

    @Transactional
    @Test
    public void addTakedDisc_newOne_success()    {
        //Arrange
        final int ownerId = 1;
        UserEntity ownerUser = new UserEntity();
        ownerUser.setId(ownerId);

        UserEntity takerUser = new UserEntity();
        final int takerId = 2;
        takerUser.setId(takerId);

        DiscEntity disc = new DiscEntity();
        disc.setId(1);

        //Act
        TakenDiscEntity addedTakenDisc = takenDiscDAO.addDisc(ownerUser, disc, takerUser, "" );

        //Assert
        Assert.assertNotNull(addedTakenDisc);
        Assert.assertEquals(takerUser, addedTakenDisc.getTakerUser());
    }

    @Transactional
    @Test
    public void takeDiscToUserById_correctOn_success()    {
        //Arrange


        final int takerUserId = 3;
        final int takenDiscId = 4;
        //Act
        boolean wasTaken = takenDiscDAO.takeDiscByIdToUser(takenDiscId, takerUserId );

        //Assert
        Assert.assertTrue(wasTaken);
    }

    @Transactional
    @Test
    public void returnDisc_validId_success()    {
        //Arrange


        final int takenDiscId = 4;
        //Act
        boolean wasReturned = takenDiscDAO.returnDiscById(takenDiscId );

        //Assert
        Assert.assertTrue(wasReturned);
    }

    @Transactional
    @Test
    public void returnDisc_invalidDiscId_notReturn()    {
        //Arrange

        final int invalidTakenDiscId = 3;
        //Act
        boolean wasReturned = takenDiscDAO.returnDiscById(invalidTakenDiscId);

        //Assert
        Assert.assertFalse(wasReturned);

    }


}
