package com.mycomp.model.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "doctorsList")
@XmlAccessorType(XmlAccessType.FIELD)
public class DoctorList {
    @XmlElement(name = "doctor")
    private List<Doctor> doctorList;

    public DoctorList(List<Doctor> doctorList){
        this.doctorList = doctorList;
    }

    public DoctorList(){
    }

    public List<Doctor> getDoctorsList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }
}
