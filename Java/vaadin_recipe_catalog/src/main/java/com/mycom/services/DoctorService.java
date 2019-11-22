package com.mycom.services;

import com.mycom.dao.DoctorDAO;
import com.mycom.dao.DoctorDAOInterface;
import com.mycom.entity.Doctor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.List;

public class DoctorService  {
    private  DoctorDAOInterface doctorDAO;
    private  SessionFactory sessionFactory;
    public DoctorService(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    public long add(Doctor doctor) throws HibernateException {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            doctorDAO = new DoctorDAO(session);
            long id  = doctorDAO.add(doctor);
            transaction.commit();
            return id;
        }
        catch (HibernateException e){
            throw e;
        }
    }

    public List<Doctor> getAll() {
        try(Session session = sessionFactory.openSession()){
            doctorDAO = new DoctorDAO(session);
            return doctorDAO.getAll();
        }
        catch (HibernateException e){
            throw e;
        }
    }


    public Doctor findById(long id) {
        try(Session session = sessionFactory.openSession()){
            doctorDAO = new DoctorDAO(session);
            return doctorDAO.findById(id);
        }
        catch (HibernateException e){
            throw e;
        }
}

    public void update(Doctor doctor) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            doctorDAO = new DoctorDAO(session);
            doctorDAO.update(doctor);
            transaction.commit();
        }
        catch (HibernateException e){
            throw e;
        }
    }


    public void delete(Doctor doctor) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            doctorDAO = new DoctorDAO(session);
            doctorDAO.delete(doctor);
            transaction.commit();
        }
        catch (HibernateException e){
            throw e;
        }
    }
}
