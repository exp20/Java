package com.mycomp.model.dao;

import com.mycomp.model.entity.EmailHistory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmailHistoryDAO implements EmailHistoryDAOInterface {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public long add(EmailHistory emailHistory) {
        Session session = sessionFactory.getCurrentSession();
        long id = (long) session.save(emailHistory); //сохраняем в бд генерируя новый id даже если установлен
        return id;

    }

    @Override
    public List<EmailHistory> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<EmailHistory> doctors = (List<EmailHistory>) session.createQuery("from EmailHistory").list();
        return doctors;
    }

    @Override
    public EmailHistory findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        EmailHistory history = session.get(EmailHistory.class,id); //получаем persist(Object)
        return history;
    }

  /*  @Override
    public void update(History history) {
        Session session = sessionFactory.getCurrentSession();
        session.update(history);
    }*/

    @Override
    public void delete(EmailHistory emailHistory) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(emailHistory);
    }
}
