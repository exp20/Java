package com.mycom.services;


import com.mycom.dao.PatientDAO;
import com.mycom.dao.PatientDAOInterface;
import com.mycom.entity.Patient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PatientService {
    private PatientDAOInterface patientDAO;
    private SessionFactory sessionFactory;

    public PatientService(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    public long add(Patient patient) throws HibernateException {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            patientDAO = new PatientDAO(session);
            long id  = patientDAO.add(patient);
            transaction.commit();
            return id;
        }
        catch (HibernateException e){
            throw e;
        }
    }

    public List<Patient> getAll() {
        try(Session session = sessionFactory.openSession()){
            patientDAO = new PatientDAO(session);
            return patientDAO.getAll();
        }
        catch (HibernateException e){
            throw e;
        }
    }


    public Patient findById(long id) {
        try(Session session = sessionFactory.openSession()){
            patientDAO = new PatientDAO(session);
            return patientDAO.findById(id);
        }
        catch (HibernateException e){
            throw e;
        }
    }

    public void update(Patient doctor) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            patientDAO = new PatientDAO(session);
            patientDAO.update(doctor);
            transaction.commit();
        }
        catch (HibernateException e){
            throw e;
        }
    }


    public void delete(Patient doctor) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            patientDAO = new PatientDAO(session);
            patientDAO.delete(doctor);
            transaction.commit();
        }
        catch (HibernateException e){
            throw e;
        }
    }
}
