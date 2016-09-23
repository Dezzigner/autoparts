package com.d1l.dao;

import com.d1l.model.Car;
import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CarDao {

    public static void addOrUpdateCar(Car car) {
        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(car);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteCar(int id) {

        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            Car car = session.get(Car.class, id);

            if (car != null) {
                session.delete(car);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    public static Car getCarById(int id) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Car car = null;
        try {
            Criteria criteria = session.createCriteria(Car.class);
            criteria.add(Restrictions.eq("id", id));
            car = (Car)criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return car;
    }

    public static List<Car> getCarsList() {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<Car> carsList = null;
        try {
            carsList = (List<Car>)session.createCriteria(Car.class).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return carsList;
    }


}
