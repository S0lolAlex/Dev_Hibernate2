package org.greenSnake.CRUD;

import org.greenSnake.data.Client;
import org.greenSnake.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.hql.HqlInterpretationException;

import java.util.List;

public class ClientCrudService {
    private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();


    public boolean create(Client client) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
            return true;
        }catch (HqlInterpretationException e){
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public Client getById(long id) {
        try (Session session = sessionFactory.openSession()){
            return session.get(Client.class, id);
        }catch (HqlInterpretationException e){
            e.printStackTrace();
            return null;
        }
    }

    public void update(Client client) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
        }catch (HqlInterpretationException e){
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public boolean isDelete(Client client) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.remove(client);
            transaction.commit();
            return true;
        } catch (HqlInterpretationException e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public List<Client> listAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("from Client", Client.class).list();
        } catch (HqlInterpretationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
