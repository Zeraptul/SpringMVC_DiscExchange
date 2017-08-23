package com.discExchange.dao;

import com.discExchange.model.OrderEntity;
import com.discExchange.model.OrderLineEntity;
import lombok.Setter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Setter
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<OrderEntity> getAllOrders() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from OrderEntity order by id");
        List<OrderEntity> result = query.list();
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<OrderLineEntity> getAllOrderLines() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from OrderLineEntity order by id");
        List<OrderLineEntity> result = query.list();
        return result;
    }
}
