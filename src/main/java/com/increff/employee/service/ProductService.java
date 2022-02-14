package com.increff.employee.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.increff.employee.dao.BrandDao;
import com.increff.employee.dao.ProductDao;
import com.increff.employee.pojo.ProductPojo;
import com.increff.employee.util.StringUtil;

@Service
public class ProductService {

    @Autowired
    private ProductDao dao;

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private InventoryService inventoryService;


    @Transactional(rollbackOn = ApiException.class)
    public void add(ProductPojo p) throws ApiException {
        if (brandDao.select(p.getBrand_category()) == null) {
            throw new ApiException("Brand doesn't exist");
        }
        normalize(p);
        if (StringUtil.isEmpty(p.getName())) {
            throw new ApiException("name cannot be empty");
        }
        dao.insert(p);
    }

    @Transactional
    public void delete(int id) {
        inventoryService.delete(id);
        dao.delete(id);
        // orderItemDao.deleteFromProductId(id);
    }

    @Transactional
    public void deleteFromBrandId(int brandId) {
        dao.deleteFromBrandId(brandId);
    }


    @Transactional(rollbackOn = ApiException.class)
    public ProductPojo get(int id) throws ApiException {
        return getCheck(id);
    }

    @Transactional
    public List<ProductPojo> getAll() {
        return dao.selectAll();
    }

    @Transactional(rollbackOn = ApiException.class)
    public void update(int id, ProductPojo p) throws ApiException {
        if (brandDao.select(p.getBrand_category()) == null) {
            throw new ApiException("Brand doesn't exist");
        }
        normalize(p);
        ProductPojo ex = getCheck(id);
        ex.setBarcode(p.getBarcode());
        ex.setMrp(p.getMrp());
        ex.setBrand_category(p.getBrand_category());
        ex.setName(p.getName());
        dao.update(ex);
    }

    @Transactional
    public ProductPojo getCheck(int id) throws ApiException {
        ProductPojo p = dao.select(id);
        if (p == null) {
            throw new ApiException("Product with given ID does not exit, id: " + id);
        }
        return p;
    }

    protected static void normalize(ProductPojo p) {
        p.setName(StringUtil.toLowerCase(p.getName()));
    }

    public List<ProductPojo> selectFromBrandId(int id) {
        return dao.selectFromBrandId(id);
    }
}
