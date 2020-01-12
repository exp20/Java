package com.mycomp.dao;


import com.mycomp.domain.Doctor;
import org.hibernate.Session;

import java.util.List;

public class DoctorDAO implements DoctorDAOInterface {

    private Session session;
    public DoctorDAO(Session session){
        this.session = session;
    }

    @Override
    public long add(Doctor doctor) {
        return (long)  session.save(doctor); //сохраняем в бд генерируя новый id даже если установлен
    }

    @Override
    public List<Doctor> getAll() {
        List<Doctor> doctors = (List<Doctor>) session.createQuery("from Doctor").list();
        return doctors;
    }

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
    }
}
