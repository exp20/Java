package com.mycom.services;

import com.mycom.dao.DoctorDAO;
import com.mycom.dao.DoctorDAOInterface;
import com.mycom.entity.Doctor;


import com.mycom.utils.HibernateSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;


import java.util.List;

public class DoctorService  {
    private  DoctorDAOInterface doctorDAO;
    private  SessionFactory sessionFactory;

    public DoctorService() throws Exception{
        try {
            this.sessionFactory = HibernateSession.getSessionFactory();
        } catch (Exception e) {
            throw e;
        }
    }
    public DoctorService(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    public long add(Doctor doctor) throws Exception {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            doctorDAO = new DoctorDAO(session);
            long id  = doctorDAO.add(doctor);
            transaction.commit();
            return id;
        }
        catch (Exception e){
            throw e;
        }
    }

    public List<Doctor> getAll() throws Exception {
        try(Session session = sessionFactory.openSession()){
            doctorDAO = new DoctorDAO(session);
            return doctorDAO.getAll();
        }
        catch (Exception e){
            throw e;
        }
    }


    public Doctor findById(long id) throws Exception {
        try(Session session = sessionFactory.openSession()){
            doctorDAO = new DoctorDAO(session);
            return doctorDAO.findById(id);
        }
        catch (Exception e){
            throw e;
        }
}

    public void update(Doctor doctor) throws Exception {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            doctorDAO = new DoctorDAO(session);
            doctorDAO.update(doctor);
            transaction.commit();
        }
        catch (Exception e){
            throw e;
        }
    }


    public void delete(Doctor doctor) throws Exception {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            doctorDAO = new DoctorDAO(session);
            doctorDAO.delete(doctor);
            transaction.commit();
        }

        catch (Exception e){
            throw e;
        }
    }
}
