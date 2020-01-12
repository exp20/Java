package com.mycomp.dao;


import com.mycomp.domain.Patient;

import java.util.List;

public interface PatientDAOInterface {
    //CRUD
    long add(Patient patient);
    List<Patient> getAll();
    Patient findById(long id);
    void update(Patient patient);
    void delete(Patient patient);
}
