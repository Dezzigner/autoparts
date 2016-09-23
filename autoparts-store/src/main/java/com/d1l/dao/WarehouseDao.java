package com.d1l.dao;

import com.d1l.model.Warehouse;
import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class WarehouseDao {

    public static void addOrUpdateWarehouse(Warehouse warehouse) {
        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(warehouse);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteWarehouse(int id) {

        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            Warehouse warehouse = session.get(Warehouse.class, id);

            if (warehouse != null) {
                session.delete(warehouse);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    public static Warehouse getWarehouseById(int id) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Warehouse warehouse = null;
        try {
            Criteria criteria = session.createCriteria(Warehouse.class);
            criteria.add(Restrictions.eq("id", id));
            warehouse = (Warehouse)criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return warehouse;
    }

    public static List<Warehouse> getWarehousesList() {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<Warehouse> warehousesList = null;
        try {
            warehousesList = (List<Warehouse>)session.createCriteria(Warehouse.class).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return warehousesList;
    }
    
}
