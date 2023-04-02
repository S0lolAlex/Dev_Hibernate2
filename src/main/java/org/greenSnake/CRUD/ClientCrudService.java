package org.greenSnake.CRUD;

import org.greenSnake.data.Client;
import org.greenSnake.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.hql.HqlInterpretationException;

import java.util.List;

public class ClientCrudService {
    private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();


    public boolean create(Client client) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(client);
            transaction.commit();
            return true;
        }catch (HqlInterpretationException e){
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public String getById(long id) {
        Session session = sessionFactory.openSession();
        try {
            Client client = session.get(Client.class, id);
            return client.getName();
        }catch (HqlInterpretationException e){
            e.printStackTrace();
            return "error";
        }
    }

    public void setName(long id, String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Client client = session.get(Client.class, id);
            client.setName(name);
            session.persist(client);
            transaction.commit();
        }catch (HqlInterpretationException e){
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public boolean isDelete(Client client) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.remove(client);
            transaction.commit();
            return true;
        } catch (HqlInterpretationException e) {
            return false;
        }
    }

    public List<Client> listAll() {
        Session session = sessionFactory.openSession();
        try {
            Query<Client> clients = session.createQuery("from Client", Client.class);
            List<Client> list = clients.list();
            session.close();
            return list;
        } catch (HqlInterpretationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
