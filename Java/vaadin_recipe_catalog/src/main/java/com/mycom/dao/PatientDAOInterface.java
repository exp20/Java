package com.mycom.dao;

import com.mycom.entity.Patient;

import java.util.List;

public interface PatientDAOInterface {
    //CRUD
    long add(Patient patient);
    List<Patient> getAll();
    Patient findById(long id);
    void update(Patient patient);
    void delete(Patient patient);
}