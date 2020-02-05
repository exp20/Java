package com.mycomp.model.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table (name = "\"recipes\"",schema = "\"PUBLIC\"")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Recipe {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "\"id\"")
    @XmlElement
    private long id;

    @Column (name = "\"description\"")
    @XmlElement
    private String description;

    @Column (name = "\"priority\"")
    @XmlElement
    private String priority;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="\"doctor_id\"", nullable=false)
    @XmlElement
    private Doctor doctor;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="\"patient_id\"", nullable=false)
    @XmlElement
    private Patient patient;

    //  @Column (name = "\"creation_date\"")
    //  private Date creation_date;
    //  @Column (name = "\"validity\"")
    //  private Date validity;
    public Recipe (){

    }

 /*   public Recipe(String description, Date creation_date, Date validity, String priority, Doctor doctor,  Patient patient){
        this.description=description;
        this.creation_date = creation_date;
        this.doctor=doctor;
        this.patient=patient;
        this.validity = validity;
        this.priority =priority;
    }*/
 public Recipe(String description, String priority, Doctor doctor,  Patient patient){
     this.description=description;
     this.doctor=doctor;
     this.patient=patient;
     this.priority =priority;
 }

    public long getId(){return id;}


    public String  getDescription(){
        return description;
    }
 /*   public Date getCreation_date(){
        return creation_date;
    }
    public Date getValidity(){
        return validity;
    }*/
    public Doctor getDoctor(){
        return doctor;
    }
    public Patient getPatient(){
        return patient;
    }
    public String getPriority(){
        return priority;
    }

    public void  setDescription (String description){
        this.description = description;
    }
    /*
    public void  setCreation_date(Date creation_date){
        this.creation_date = creation_date;
    }
    public  void setValidity(Date validity){
        this.validity = validity;
    }*/
    public  void  setPriority (String priority){
        this.priority = priority;
    }
    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
    }
    public void setPatient(Patient patient){
        this.patient = patient;
    }

 /*   public String toString(){
        return new String("recipe[ "+id+", "+doctor.getId()+", "+patient.getId()+", "+creation_date+", "+validity+", "+description+"]");
    }*/

    public String toString(){
        return new String("recipe[ "+id+", "+doctor.getId()+", "+patient.getId()+", "+", "+description+"]");
    }
}
