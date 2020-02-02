package com.mycomp.services;


import com.mycomp.model.dao.PatientDAO;
import com.mycomp.model.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    private PatientDAO patientDAO;

    @Autowired
    public void setPatientDAO(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @Transactional
    public long add(Patient patient) throws Exception {
        return patientDAO.add(patient);
    }

    @Transactional
    public List<Patient> getAll() throws Exception {
       return patientDAO.getAll();
    }

    @Transactional
    public Patient findById(long id) throws Exception {
        return patientDAO.findById(id);
    }

    @Transactional
    public void update(Patient patient) throws Exception {
        patientDAO.update(patient);
    }

    @Transactional
    public void delete(Patient patient) throws Exception {
       patientDAO.delete(patientDAO.findById(patient.getId()));
    }

}

