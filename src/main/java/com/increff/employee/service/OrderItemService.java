package com.increff.employee.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.increff.employee.dao.OrderDao;
import com.increff.employee.dao.OrderItemDao;
import com.increff.employee.dao.ProductDao;
import com.increff.employee.pojo.OrderItemPojo;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemDao dao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;


    @Transactional(rollbackOn = ApiException.class)
    public void add(OrderItemPojo p) throws ApiException {
        if (orderDao.select(p.getOrderId()) == null) {
            throw new ApiException("Order doesn't exist");
        }
        if (productDao.select(p.getProductId()) == null) {
            throw new ApiException("Product doesn't exist");
        }
        if (p.getQuantity() < 0) {
            throw new ApiException("Quantity can not be negative");
        }
        // if(inventoryDao.select(p.getProductId()).getQuantity()>=p.getQuantity()){

        // }
        dao.insert(p);
    }

    @Transactional
    public void delete(int id) {
        dao.delete(id);
    }

    @Transactional
    public void deleteByProductId(int productId) {
        dao.deleteByProductId(productId);
    }

    @Transactional
    public List<OrderItemPojo> getByOrderId(int productId) {
        return dao.getByOrderId(productId);
    }

    @Transactional(rollbackOn = ApiException.class)
    public OrderItemPojo get(int id) throws ApiException {
        return getCheck(id);
    }

    @Transactional
    public List<OrderItemPojo> getAll() {
        return dao.selectAll();
    }

    @Transactional(rollbackOn = ApiException.class)
    public void update(int id, OrderItemPojo p) throws ApiException {
        if (orderDao.select(p.getOrderId()) == null) {
            throw new ApiException("Order doesn't exist");
        }
        if (productDao.select(p.getProductId()) == null) {
            throw new ApiException("Product doesn't exist");
        }
        if (p.getQuantity() < 0) {
            throw new ApiException("Quantity can not be negative");
        }
        OrderItemPojo ex = getCheck(id);
        ex.setProductId(p.getProductId());
        ex.setQuantity(p.getQuantity());
        ex.setSellingPrice(p.getSellingPrice());
        dao.update(ex);
    }

    @Transactional
    public OrderItemPojo getCheck(int id) throws ApiException {
        OrderItemPojo p = dao.select(id);
        if (p == null) {
            throw new ApiException("OrderItem with given ID does not exit, id: " + id);
        }
        return p;
    }


}
