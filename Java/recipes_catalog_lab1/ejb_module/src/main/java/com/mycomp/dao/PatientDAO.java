package com.mycomp.dao;





import com.mycomp.domain.Patient;
import org.hibernate.Session;

import java.util.List;

public class PatientDAO implements PatientDAOInterface{
    private Session session;
    public PatientDAO(Session session){
        this.session = session;
    }

    @Override
    public long add(Patient patient) {
       return (long) session.save(patient); //сохраняем в бд генерируя новый id даже если установлен
    }

    @Override
    public List<Patient> getAll() {
        List<Patient> patients = (List<Patient>) session.createQuery("from Patient").list();
        return patients;
    }

    @Override
    public Patient findById(long id) {
        return session.get(Patient.class,id); //получаем persist(Object)
    }

    @Override
    public void update(Patient patient) {
        session.update(patient);
    }

    @Override
    public void delete(Patient patient) {
        session.delete(patient);
    }
}

