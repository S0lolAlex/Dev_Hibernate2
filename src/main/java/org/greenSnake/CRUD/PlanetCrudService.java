package org.greenSnake.CRUD;

import org.greenSnake.data.Planet;
import org.greenSnake.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.hql.HqlInterpretationException;

import java.util.List;

public class PlanetCrudService {
    private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

    public boolean create(Planet planet) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();
            session.persist(planet);
            transaction.commit();
            return true;
        } catch (HqlInterpretationException e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public Planet getById(String id) {
        try (Session session = sessionFactory.openSession()){
            return session.find(Planet.class, id);
        } catch (HqlInterpretationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(Planet planet) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.update(planet);
            transaction.commit();
        } catch (HqlInterpretationException e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public boolean delete(Planet planet) {
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
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("from Planet", Planet.class).list();
        } catch (HqlInterpretationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
