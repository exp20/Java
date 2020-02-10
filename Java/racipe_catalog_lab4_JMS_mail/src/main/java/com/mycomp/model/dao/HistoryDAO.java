package com.mycomp.model.dao;


import com.mycomp.model.entity.History;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public class HistoryDAO implements HistoryDAOInterface {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public long add(History history) {
        Session session = sessionFactory.getCurrentSession();
        long id = (long) session.save(history); //сохраняем в бд генерируя новый id даже если установлен
        return id;

    }

    @Override
    public List<History> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<History> doctors = (List<History>) session.createQuery("from History").list();
        return doctors;
    }

    @Override
    public History findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        History history = session.get(History.class,id); //получаем persist(Object)
        return history;
    }

  /*  @Override
    public void update(History history) {
        Session session = sessionFactory.getCurrentSession();
        session.update(history);
    }*/

    @Override
    public void delete(History history) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(history);
    }
}