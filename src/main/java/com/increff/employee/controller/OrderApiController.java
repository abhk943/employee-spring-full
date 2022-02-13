package com.increff.employee.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.increff.employee.model.OrderForm;
import com.increff.employee.pojo.OrderPojo;
import com.increff.employee.service.ApiException;
import com.increff.employee.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
public class OrderApiController {

    @Autowired
    private OrderService service;

    @ApiOperation(value = "Adds an order")
    @RequestMapping(path = "/api/order", method = RequestMethod.POST)
    public void add() throws ApiException {
        OrderPojo p = new OrderPojo();
        service.add(p);
    }


    @ApiOperation(value = "Deletes and order")
    @RequestMapping(path = "/api/order/{id}", method = RequestMethod.DELETE)
    // /api/1
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @ApiOperation(value = "Gets an order by ID")
    @RequestMapping(path = "/api/order/{id}", method = RequestMethod.GET)
    public OrderForm get(@PathVariable int id) throws ApiException {
        OrderPojo p = service.get(id);
        return convert(p);
    }

    @ApiOperation(value = "Gets list of all order")
    @RequestMapping(path = "/api/order", method = RequestMethod.GET)
    public List<OrderForm> getAll() {
        List<OrderPojo> list = service.getAll();
        List<OrderForm> list2 = new ArrayList<OrderForm>();
        for (OrderPojo p : list) {
            list2.add(convert(p));
        }
        return list2;
    }

    @ApiOperation(value = "Updates an order")
    @RequestMapping(path = "/api/order/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable int id, @RequestBody OrderForm f) throws ApiException {
        OrderPojo p = convert(f);
        service.update(id, p);
    }


    private static OrderForm convert(OrderPojo p) {
        OrderForm d = new OrderForm();
        d.setTime(p.getTime());
        d.setId(p.getId());
        return d;
    }

    private static OrderPojo convert(OrderForm f) {
        OrderPojo p = new OrderPojo();
        p.setTime(f.getTime());
        p.setId(f.getId());
        return p;
    }

}
