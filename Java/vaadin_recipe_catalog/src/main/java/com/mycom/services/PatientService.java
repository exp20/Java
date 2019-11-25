package com.mycom.services;


import com.mycom.dao.PatientDAO;
import com.mycom.dao.PatientDAOInterface;
import com.mycom.entity.Patient;
import com.mycom.utils.HibernateSession;
import org.hibernate.*;

import java.util.List;

public class PatientService {
    private PatientDAOInterface patientDAO;
    private SessionFactory sessionFactory;

    public PatientService() throws Exception
    {
        try {
            this.sessionFactory = HibernateSession.getSessionFactory();
        } catch (Exception e) {
            throw  e;
        }
    }

    public PatientService(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }




    public long add(Patient patient) throws Exception {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            patientDAO = new PatientDAO(session);
            long id  = patientDAO.add(patient);
            transaction.commit();
            return id;
        }
        catch (Exception e){
            throw e;
        }
    }


    public List<Patient> getAll() throws Exception{
        try(Session session = sessionFactory.openSession()){
            patientDAO = new PatientDAO(session);
            return patientDAO.getAll();
        }
        catch (Exception e){
            throw e;
        }
    }


    public Patient findById(long id) throws Exception {
        try(Session session = sessionFactory.openSession()){
            patientDAO = new PatientDAO(session);
            return patientDAO.findById(id);
        }
        catch (Exception e){
            throw e;
        }
    }

    public void update(Patient doctor) throws Exception {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            patientDAO = new PatientDAO(session);
            patientDAO.update(doctor);
            transaction.commit();
        }
        catch (Exception e){
            throw e;
        }
    }


    public void delete(Patient doctor) throws Exception {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            patientDAO = new PatientDAO(session);
            patientDAO.delete(doctor);
            transaction.commit();
        }
        catch (Exception e){
            throw e;
        }
    }
}
