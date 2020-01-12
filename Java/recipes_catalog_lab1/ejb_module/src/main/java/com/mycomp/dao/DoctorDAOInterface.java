package com.mycomp.dao;


import com.mycomp.domain.Doctor;

import java.util.List;

public interface DoctorDAOInterface {
    //CRUD
    long add(Doctor doctor);
    List<Doctor> getAll();
    Doctor findById(long id);
    void update(Doctor doctor);
    void delete(Doctor doctor);
}
