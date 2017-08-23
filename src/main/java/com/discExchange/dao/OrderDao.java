package com.discExchange.dao;

import com.discExchange.model.OrderEntity;
import com.discExchange.model.OrderLineEntity;

import java.util.List;

public interface OrderDao {
    List<OrderEntity> getAllOrders();
    List<OrderLineEntity> getAllOrderLines();
}
