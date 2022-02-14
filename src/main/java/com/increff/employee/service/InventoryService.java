package com.increff.employee.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.increff.employee.dao.InventoryDao;
import com.increff.employee.dao.OrderItemDao;
import com.increff.employee.dao.ProductDao;
import com.increff.employee.pojo.InventoryPojo;

@Service
public class InventoryService {

    @Autowired
    private InventoryDao dao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private OrderItemService orderItemService;

    @Transactional(rollbackOn = ApiException.class)
    public void add(InventoryPojo p) throws ApiException {
        if (productDao.select(p.getId()) == null) {
            throw new ApiException("Product doesn't exist");
        }
        if (p.getQuantity() < 0) {
            throw new ApiException("Quantity can not be negative");
        }
        dao.insert(p);
    }

    @Transactional
    public void delete(int id) {
        orderItemService.deleteFromProductId(id);
        dao.delete(id);
    }

    @Transactional(rollbackOn = ApiException.class)
    public InventoryPojo get(int id) throws ApiException {
        return getCheck(id);
    }

    @Transactional
    public List<InventoryPojo> getAll() {
        return dao.selectAll();
    }

    @Transactional(rollbackOn = ApiException.class)
    public void update(int id, InventoryPojo p) throws ApiException {
        if (productDao.select(p.getId()) == null) {
            throw new ApiException("Product doesn't exist");
        }
        InventoryPojo ex = getCheck(id);
        if (p.getQuantity() < 0) {
            throw new ApiException("Quantity can not be negative");
        }
        ex.setQuantity(p.getQuantity());
        dao.update(ex);
    }

    @Transactional
    public InventoryPojo getCheck(int id) throws ApiException {
        InventoryPojo p = dao.select(id);
        if (p == null) {
            throw new ApiException("Inventory with given ID does not exit, id: " + id);
        }
        return p;
    }

}
