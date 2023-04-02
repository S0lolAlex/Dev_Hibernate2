package org.greenSnake.CRUD;

import org.greenSnake.Utils.HibernateUtil;
import org.greenSnake.data.Ticket;
import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.hql.HqlInterpretationException;

import java.util.Date;
import java.util.List;

public class TicketCrudService {
    private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

    public boolean create(Ticket ticket) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(ticket);
            transaction.commit();
            return true;
        } catch (PropertyValueException e) {
            return false;
        }
    }

    public Ticket getById(long id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Ticket.class, id);
        } catch (HqlInterpretationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(Ticket ticket) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(ticket);
            transaction.commit();
        } catch (HqlInterpretationException e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public boolean isDelete(Ticket ticket) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.remove(ticket);
            transaction.commit();
            return true;
        } catch (HqlInterpretationException e) {
            return false;
        }
    }

    public List<Ticket> listAll() {
        Session session = sessionFactory.openSession();
        try {
            Query<Ticket> ticket = session.createQuery("from Ticket", Ticket.class);
            return ticket.list();
        } catch (HqlInterpretationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
