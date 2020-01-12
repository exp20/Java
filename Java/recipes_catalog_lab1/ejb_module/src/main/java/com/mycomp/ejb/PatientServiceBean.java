package com.mycomp.ejb;

import com.mycomp.dao.DoctorDAO;
import com.mycomp.dao.PatientDAO;
import com.mycomp.domain.Doctor;
import com.mycomp.domain.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Stateless
public class PatientServiceBean implements PatientService {
    private PatientDAO patientDAO;

    @PersistenceContext(unitName = "myUnit") // работа с jpa.
    private EntityManager entityManager;



    public long add(Patient patient) throws Exception {
        Session session = entityManager.unwrap(Session.class);
        patientDAO = new PatientDAO(session);
        Transaction transaction = session.beginTransaction();
        long id  = patientDAO.add(patient);
        transaction.commit();
        return id;
    }

    public List<Patient> getAll() throws Exception {
        Session session = entityManager.unwrap(Session.class);
        patientDAO = new PatientDAO(session);
        return patientDAO.getAll();
    }


    public Patient findById(long id) throws Exception {
        Session session = entityManager.unwrap(Session.class);
        patientDAO = new PatientDAO(session);
        return patientDAO.findById(id);
    }

    public void update(Patient doctor) throws Exception {
        Session session = entityManager.unwrap(Session.class);
        patientDAO = new PatientDAO(session);
        Transaction transaction = session.beginTransaction();
        patientDAO.update(doctor);
        transaction.commit();
    }

    public void delete(Patient doctor) throws Exception {
        Session session = entityManager.unwrap(Session.class);
        patientDAO = new PatientDAO(session);
        Transaction transaction = session.beginTransaction();
        patientDAO.delete(findById(doctor.getId()));
        transaction.commit();
    }

}

