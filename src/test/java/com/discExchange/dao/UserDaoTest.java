package com.discExchange.dao;

import com.discExchange.dao.UserDAO;
import com.discExchange.model.UserEntity;
import org.hsqldb.util.DatabaseManagerSwing;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by adMin on 12.08.2017.
 */
//@WebAppConfiguration("classpath:spring/servlet-context-test.xml")
//@Import(value = UserDaoTest.TestConfig.class)
//@RunWith(SpringRunner.class)
@PropertySource("classpath:jdbc-test.properties ")
@ContextConfiguration("classpath:spring/servlet-context-test_2.xml")
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {

    @Autowired
    @Qualifier("userDAO")
    UserDAO userDao;

    @Transactional
    @Test
    public void getUserById_hasUser_success() {
        UserEntity user = userDao.getUserById(1);
        Assert.assertNotNull(user);
        Assert.assertEquals("test", user.getNickname());
    }

    @Transactional
    @Test
    public void getUserById_noUser_success() {
        UserEntity user = userDao.getUserById(0);
        Assert.assertNull(user);
    }

    @Transactional
    @Test
    public void findUserByNickname_hasUser_success() {
        String nickname = "test";
        UserEntity user = userDao.findUserByNamePassword(nickname, "test");
        Assert.assertNotNull(user);
        Assert.assertEquals(nickname, user.getNickname());
    }

    @Transactional
    @Test
    public void findUserByNickname_noUser_willNull() {
        UserEntity user = userDao.findUserByNamePassword("gleb2", "gleb");
        Assert.assertNull(user);
    }

    @Transactional
    @Test
    public void findUserByNickname_incorrectPassword_willNull() {
        UserEntity user = userDao.findUserByNamePassword("user2", "gleb");
        Assert.assertNull(user);
    }

    @Transactional
    @Test
    public void findUserByNickname_invalidHashInDb_willNull() {
        UserEntity user = userDao.findUserByNamePassword("user6", "gleb");
        Assert.assertNull(user);
    }
}


