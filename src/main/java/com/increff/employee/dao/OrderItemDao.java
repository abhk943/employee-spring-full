package com.increff.employee.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.increff.employee.pojo.OrderItemPojo;

@Repository
public class OrderItemDao extends AbstractDao {

    private static String delete_id = "delete from OrderItemPojo p where id=:id";
    private static String DELETE_FROM_PRODUCT_ID =
            "delete from OrderItemPojo p where productId=:productId";
    private static String select_id = "select p from OrderItemPojo p where id=:id";
    private static final String SELECT_BY_ORDER_ID =
            "select p from OrderItemPojo p where orderId=:orderId";
    private static String select_all = "select p from OrderItemPojo p";

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void insert(OrderItemPojo p) {
        em.persist(p);
    }

    public int delete(int id) {
        Query query = em.createQuery(delete_id);
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    public int deleteFromProductId(int productId) {
        Query query = em.createQuery(DELETE_FROM_PRODUCT_ID);
        query.setParameter("productId", productId);
        return query.executeUpdate();
    }

    public OrderItemPojo select(int id) {
        TypedQuery<OrderItemPojo> query = getQuery(select_id, OrderItemPojo.class);
        query.setParameter("id", id);
        return getSingle(query);
    }

    public List<OrderItemPojo> selectAll() {
        TypedQuery<OrderItemPojo> query = getQuery(select_all, OrderItemPojo.class);
        return query.getResultList();
    }

    public void update(OrderItemPojo p) {}

    public List<OrderItemPojo> getByOrderId(int orderId) {
        TypedQuery<OrderItemPojo> query = getQuery(SELECT_BY_ORDER_ID, OrderItemPojo.class);
        query.setParameter("orderId", orderId);
        return query.getResultList();
    }



}
