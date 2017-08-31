package com.discExchange.dao;

import com.discExchange.model.OrderEntity;
import com.discExchange.model.OrderLineEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ContextConfiguration("classpath:spring/servlet-context-test_2.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderDaoTest {

    @Autowired(required = true)
    @Qualifier("orderDao")
    OrderDao orderDao;

    @Test
    @Transactional
    public void getAllOrders_all_success() {
        List<OrderEntity> orderEntityList = orderDao.getAllOrders();
        Assert.assertTrue(orderEntityList.size() > 0);
    }



    @Test
    @Transactional
    public void getOrder_itsLines_success() {
        List<OrderEntity> orderEntityList = orderDao.getAllOrders();
        OrderEntity orderEntity = orderEntityList.get(0);
        Assert.assertTrue(orderEntity.getOrderLines().size() >= 2);
    }

    @Test
    @Transactional
    public void getAllOrderLines_all_success() {
        List<OrderLineEntity> allOrderLines = orderDao.getAllOrderLines();
        Assert.assertTrue(allOrderLines.size() >= 2);
    }

    @Test
    @Transactional
    public void getOrderLine_itsOrder_success() {
        List<OrderLineEntity> allOrderLines = orderDao.getAllOrderLines();
        OrderLineEntity orderLineEntity = allOrderLines.get(0);
        OrderEntity orderEntity = orderLineEntity.getOrder();
        Assert.assertNotNull(orderEntity);
        //Assert.assertNotNull(orderEntity.getRemarks());
    }
}
