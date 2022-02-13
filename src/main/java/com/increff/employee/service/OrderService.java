package com.increff.employee.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.increff.employee.dao.OrderDao;
import com.increff.employee.pojo.OrderPojo;

@Service
public class OrderService {

    @Autowired
    private OrderDao dao;


    @Transactional(rollbackOn = ApiException.class)
    public void add(OrderPojo p) throws ApiException {
        dao.insert(p);
    }

    @Transactional
    public void delete(int id) {
        dao.delete(id);
    }

    @Transactional(rollbackOn = ApiException.class)
    public OrderPojo get(int id) throws ApiException {
        return getCheck(id);
    }

    @Transactional
    public List<OrderPojo> getAll() {
        return dao.selectAll();
    }

    @Transactional(rollbackOn = ApiException.class)
    public void update(int id, OrderPojo p) throws ApiException {
        OrderPojo ex = getCheck(id);
        ex.setTime(p.getTime());
        dao.update(ex);
    }

    @Transactional
    public OrderPojo getCheck(int id) throws ApiException {
        OrderPojo p = dao.select(id);
        if (p == null) {
            throw new ApiException("Order with given ID does not exit, id: " + id);
        }
        return p;
    }

}
