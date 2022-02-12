package com.increff.employee.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.increff.employee.model.ProductData;
import com.increff.employee.model.ProductForm;
import com.increff.employee.pojo.ProductPojo;
import com.increff.employee.service.ApiException;
import com.increff.employee.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
public class ProductApiController {

    @Autowired
    private ProductService service;

    @ApiOperation(value = "Adds an product")
    @RequestMapping(path = "/api/product", method = RequestMethod.POST)
    public void add(@RequestBody ProductForm form) throws ApiException {
        ProductPojo p = convert(form);
        service.add(p);
    }


    @ApiOperation(value = "Deletes and product")
    @RequestMapping(path = "/api/product/{id}", method = RequestMethod.DELETE)
    // /api/1
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @ApiOperation(value = "Gets an product by ID")
    @RequestMapping(path = "/api/product/{id}", method = RequestMethod.GET)
    public ProductData get(@PathVariable int id) throws ApiException {
        ProductPojo p = service.get(id);
        return convert(p);
    }

    @ApiOperation(value = "Gets list of all employees")
    @RequestMapping(path = "/api/product", method = RequestMethod.GET)
    public List<ProductData> getAll() {
        List<ProductPojo> list = service.getAll();
        List<ProductData> list2 = new ArrayList<ProductData>();
        for (ProductPojo p : list) {
            list2.add(convert(p));
        }
        return list2;
    }

    @ApiOperation(value = "Updates an product")
    @RequestMapping(path = "/api/product/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable int id, @RequestBody ProductForm f) throws ApiException {
        ProductPojo p = convert(f);
        service.update(id, p);
    }


    private static ProductData convert(ProductPojo p) {
        ProductData d = new ProductData();
        d.setBarcode(p.getBarcode());
        d.setBrand_category(p.getBrand_category());
        d.setId(p.getId());
        d.setName(p.getName());
        d.setMrp(p.getMrp());
        return d;
    }

    private static ProductPojo convert(ProductForm f) {
        ProductPojo p = new ProductPojo();
        p.setBarcode(f.getBarcode());
        p.setBrand_category(f.getBrand_category());
        p.setName(f.getName());
        p.setMrp(f.getMrp());
        return p;
    }

}
