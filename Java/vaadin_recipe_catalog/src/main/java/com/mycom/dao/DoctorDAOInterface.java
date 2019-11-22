package com.mycom.dao;

import com.mycom.entity.Doctor;

import java.util.List;

public interface DoctorDAOInterface {
    //CRUD
    long add(Doctor doctor);
    List<Doctor> getAll();
    Doctor findById(long id);
    void update(Doctor doctor);
    void delete(Doctor doctor);
}
