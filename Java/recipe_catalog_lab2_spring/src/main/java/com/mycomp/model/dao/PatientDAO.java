package com.mycomp.model.dao;





import com.mycomp.model.entity.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientDAO implements PatientDAOInterface{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long add(Patient patient) {
        Session session = sessionFactory.getCurrentSession();
       return (long) session.save(patient); //сохраняем в бд генерируя новый id даже если установлен
    }

    @Override
    public List<Patient> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Patient> patients = (List<Patient>) session.createQuery("from Patient").list();
        return patients;
    }

    @Override
    public Patient findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Patient.class,id); //получаем persist(Object)
    }

    @Override
    public void update(Patient patient) {
        Session session = sessionFactory.getCurrentSession();
        session.update(patient);
    }

    @Override
    public void delete(Patient patient) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(patient);
    }
}

