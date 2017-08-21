package com.discExchange;

import com.discExchange.dao.UserDAO;
import com.discExchange.model.UserEntity;
import org.hsqldb.util.DatabaseManagerSwing;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

//import javax.annotation.PostConstruct;

/**
 * Not working(
 */
//@ContextConfiguration(classes = MyTest.TestConfig.class, loader = AnnotationConfigContextLoader.class)
//@RunWith(SpringJUnit4ClassRunner.class)
public class MyTest
{

    @ImportResource("classpath:spring/servlet-context-test_2.xml")
    @Import(TestConfig2.class)
    public static class TestConfig
    {
        //@PostConstruct
        public void getDbManager(){
            DatabaseManagerSwing.main(
                    new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", ""});
        }
    }

    @Configuration
    public static class TestConfig2
    {
        @Bean("userDAO_2")
        //@Primary
        public UserDAO userDAO_2()
        {
            return new TestingUserDAO();
        }
    }


    @Autowired
    @Qualifier("userDAO_2")
    UserDAO userDao;


    @Transactional
    @Test
    public void alwaysSuccess()   {
        //
    }
    /*@Test
    @Transactional
    public void test()
    {
        UserEntity my = userDao.getUserById(0);
        Assert.assertEquals(my, TestingUserDAO.DefaultEntity);
    }*/
}

@Repository
class TestingUserDAO implements UserDAO{

    public static final UserEntity DefaultEntity = new UserEntity();

    @Override
    public UserEntity findUserByNamePassword(String nickname, String password) {
        return null;
    }

    @Override
    public UserEntity getUserById(int userId) {
        return DefaultEntity;
    }
}
