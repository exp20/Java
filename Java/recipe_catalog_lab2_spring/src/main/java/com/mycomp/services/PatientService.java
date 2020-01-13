package com.mycomp.services;






import com.mycomp.model.entity.Patient;

import java.util.List;


public interface PatientService {

    public long add(Patient patient) throws Exception;

    public List<Patient> getAll() throws Exception;

    public Patient findById(long id) throws Exception;

    public void update(Patient doctor) throws Exception;

    public void delete(Patient doctor) throws Exception;

}
