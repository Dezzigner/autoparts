package com.d1l.dao;

import com.d1l.model.Detail;
import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DetailDao {

    public static void addOrUpdateDetail(Detail detail) {
        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(detail);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteDetail(int id) {

        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            Detail detail = session.get(Detail.class, id);

            if (detail != null) {
                session.delete(detail);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    public static Detail getDetailById(int id) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Detail detail = null;
        try {
            Criteria criteria = session.createCriteria(Detail.class);
            criteria.add(Restrictions.eq("id", id));
            detail = (Detail)criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return detail;
    }

    public static List<Detail> getDetailsList() {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<Detail> detailsList = null;
        try {
            detailsList = (List<Detail>)session.createCriteria(Detail.class).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return detailsList;
    }


}
