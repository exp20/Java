package com.mycomp.model.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "patientsList")
@XmlAccessorType(XmlAccessType.FIELD)
public class PatientList {
    @XmlElement(name = "patient")
    private List<Patient> patientList;

    public PatientList(List<Patient> patientsList){
        this.patientList = patientsList;
    }

    public PatientList(){
    }

    public List<Patient> getPatientsList() {
        return patientList;
    }

    public void setPatientsList(List<Patient> patientsList) {
        this.patientList = patientsList;
    }
}