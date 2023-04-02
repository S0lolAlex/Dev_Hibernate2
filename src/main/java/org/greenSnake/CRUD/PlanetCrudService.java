package org.greenSnake.CRUD;

import org.greenSnake.data.Client;
import org.greenSnake.data.Planet;
import org.greenSnake.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.hql.HqlInterpretationException;

import java.util.List;

public class PlanetCrudService {
    private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

    public boolean create(Planet planet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(planet);
            transaction.commit();
            return true;
        } catch (HqlInterpretationException e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public String getById(String id) {
        Session session = sessionFactory.openSession();
        try {
            Planet planet = session.get(Planet.class, id);
            return planet.getName();
        } catch (HqlInterpretationException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public void setName(String id, String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Planet planet = session.get(Planet.class, id);
            planet.setName(name);
            session.persist(planet);
            transaction.commit();
        } catch (HqlInterpretationException e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public boolean isDelete(Planet planet) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(planet);
            transaction.commit();
            return true;
        } catch (HqlInterpretationException e) {
            return false;
        }
    }

    public List<Planet> listAll() {
        Session session = sessionFactory.openSession();
        try {
            Query<Planet> planets = session.createQuery("from Planet", Planet.class);
            List<Planet> list = planets.list();
            session.close();
            return list;
        } catch (HqlInterpretationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
