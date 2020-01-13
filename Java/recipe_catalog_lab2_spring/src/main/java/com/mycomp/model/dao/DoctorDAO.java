package com.mycomp.model.dao;




import com.mycomp.model.entity.Doctor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DoctorDAO implements DoctorDAOInterface {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

  /*  public DoctorDAO(Session session){
        this.session = session;
    }*/

  /*  @Override
    public long add(Doctor doctor) {
        return (long)  session.save(doctor); //сохраняем в бд генерируя новый id даже если установлен
    }*/

    @Override
    public List<Doctor> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Doctor> doctors = (List<Doctor>) session.createQuery("from Doctor").list();
        return doctors;
    }
/*
    @Override
    public Doctor findById(long id) {
       return session.get(Doctor.class,id); //получаем persist(Object)
    }

    @Override
    public void update(Doctor doctor) {
        session.update(doctor);
    }

    @Override
    public void delete(Doctor doctor) {
        session.delete(doctor);
    }*/
}