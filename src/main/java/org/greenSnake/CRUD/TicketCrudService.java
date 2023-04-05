package org.greenSnake.CRUD;

import org.greenSnake.Utils.HibernateUtil;
import org.greenSnake.data.Ticket;
import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.hql.HqlInterpretationException;

import java.util.List;

public class TicketCrudService {
    private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

    public boolean create(Ticket ticket) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.persist(ticket);
            transaction.commit();
            return true;
        } catch (PropertyValueException e ) {
            transaction.rollback();
            return false;
        }
    }

    public Ticket getById(long id) {
        try (Session session = sessionFactory.openSession()){
            return session.find(Ticket.class, id);
        } catch (HqlInterpretationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(Ticket ticket) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.update(ticket);
            transaction.commit();
        } catch (HqlInterpretationException e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public boolean isDelete(Ticket ticket) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.remove(ticket);
            transaction.commit();
            return true;
        } catch (HqlInterpretationException e) {
            return false;
        }
    }

    public List<Ticket> listAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("from Ticket", Ticket.class).list();
        } catch (HqlInterpretationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
