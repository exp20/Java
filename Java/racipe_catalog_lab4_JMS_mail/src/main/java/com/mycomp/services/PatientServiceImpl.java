package com.mycomp.services;


import com.mycomp.model.dao.PatientDAO;
import com.mycomp.model.entity.EmailHistory;
import com.mycomp.model.entity.History;
import com.mycomp.model.entity.Patient;
import com.mycomp.services.JMS.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientDAO patientDAO;
    private MessageProducer messageProducer;

    private String EMAIL = "an.test.lab.mail@yandex.ru";

    @Autowired
    public void setPatientDAO(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @Autowired
    public void setMessageProducer(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }


    @Transactional
    public long add(Patient patient) throws Exception {
        long id = patientDAO.add(patient);
        messageProducer.sendMessage(new History(Long.toString(id), "Insert", patient.getClass().getName()));
        if (patient.getName().equals("Email") || patient.getName().equals("email")){
            messageProducer.sendMessage(new EmailHistory(EMAIL,"Email notification!  Patient " + id +" has been inserted"));
        }
        return id;
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
        messageProducer.sendMessage(new History(Long.toString(patient.getId()), "Update", patient.getClass().getName()));

    }

    @Transactional
    public void delete(Patient patient) throws Exception {
       patientDAO.delete(patientDAO.findById(patient.getId()));
       messageProducer.sendMessage(new History(Long.toString(patient.getId()), "Delete", patient.getClass().getName()));
        if (patient.getName().equals("Email") || patient.getName().equals("email")){
            messageProducer.sendMessage(new EmailHistory(EMAIL,"Email notification!  Patient " + patient.getId() +" has been inserted"));
        }
    }

}

