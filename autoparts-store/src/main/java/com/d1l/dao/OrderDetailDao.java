package com.d1l.dao;

import com.d1l.model.OrderDetail;
import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class OrderDetailDao {

    public static void addOrUpdateOrderDetail(OrderDetail orderDetail) {
        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(orderDetail);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteOrderDetail(int id) {

        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            OrderDetail orderDetail = session.get(OrderDetail.class, id);

            if (orderDetail != null) {
                session.delete(orderDetail);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    public static List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<OrderDetail> orderDetailsList = null;
        try {
            Criteria criteria = session.createCriteria(OrderDetail.class);
            criteria.add(Restrictions.eq("orderId", orderId));
            orderDetailsList = (List<OrderDetail>)criteria.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return orderDetailsList;
    }

    public static List<OrderDetail> getOrderDetailsList() {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<OrderDetail> orderDetailsList = null;
        try {
            orderDetailsList = (List<OrderDetail>)session.createCriteria(OrderDetail.class).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return orderDetailsList;
    }


}
