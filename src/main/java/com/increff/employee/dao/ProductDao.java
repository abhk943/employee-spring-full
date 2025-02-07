package com.increff.employee.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.increff.employee.pojo.ProductPojo;

@Repository
public class ProductDao extends AbstractDao {

    private static String delete_id = "delete from ProductPojo p where id=:id";
    private static String select_id = "select p from ProductPojo p where id=:id";
    private static String select_all = "select p from ProductPojo p";
    private static final String SELECT_FROM_BRAND_ID =
            "select p from ProductPojo p where brand_category=:brandId";

    private static final String DELETE_FROM_BRAND_ID =
            "delete from ProductPojo p where brand_category=:brandId";

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void insert(ProductPojo p) {
        em.persist(p);
    }

    public int delete(int id) {
        Query query = em.createQuery(delete_id);
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    public ProductPojo select(int id) {
        TypedQuery<ProductPojo> query = getQuery(select_id, ProductPojo.class);
        query.setParameter("id", id);
        return getSingle(query);
    }

    public List<ProductPojo> selectFromBrandId(int brandId) {
        TypedQuery<ProductPojo> query = getQuery(SELECT_FROM_BRAND_ID, ProductPojo.class);
        query.setParameter("brandId", brandId);
        return query.getResultList();

    }

    public List<ProductPojo> deleteFromBrandId(int brandId) {
        TypedQuery<ProductPojo> query = getQuery(DELETE_FROM_BRAND_ID, ProductPojo.class);
        query.setParameter("brandId", brandId);
        return query.getResultList();

    }


    public List<ProductPojo> selectAll() {
        TypedQuery<ProductPojo> query = getQuery(select_all, ProductPojo.class);
        return query.getResultList();
    }

    public void update(ProductPojo p) {}



}
