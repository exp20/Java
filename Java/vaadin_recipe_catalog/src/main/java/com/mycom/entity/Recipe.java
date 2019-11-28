package com.mycom.entity;

import com.mycom.services.RecipePriorities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table (name = "\"recipes\"",schema = "\"PUBLIC\"")
public class Recipe {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "\"id\"")
    private long id;
    @Column (name = "\"description\"")
    private String description;
    @Column (name = "\"creation_date\"")
    private Date creation_date;
    @Column (name = "\"validity\"")
    private Date validity;
    @Column (name = "\"priority\"")
    private String priority;

    @ManyToOne
    @JoinColumn(name="\"doctor_id\"", nullable=false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name="\"patient_id\"", nullable=false)
    private Patient patient;

    public Recipe (){

    }

    public Recipe(String description, Date creation_date, Date validity, String priority, Doctor doctor,  Patient patient){
        this.description=description;
        this.creation_date = creation_date;
        this.doctor=doctor;
        this.patient=patient;
        this.validity = validity;
        this.priority =priority;
    }

    public long getId(){return id;}
    public String  getDescription(){
        return description;
    }
    public Date getCreation_date(){
       return creation_date;
    }
    public Date getValidity(){
        return validity;
    }
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
    public void  setCreation_date(Date creation_date){
        this.creation_date = creation_date;
    }
    public  void setValidity(Date validity){
        this.validity = validity;
    }
    public  void  setPriority (String priority){
        this.priority = priority;
    }
    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
    }
    public void setPatient(Patient patient){
        this.patient = patient;
    }

    public String toString(){
        return new String("recipe[ "+id+", "+doctor.getId()+", "+patient.getId()+", "+creation_date+", "+validity+", "+description+"]");
    }
}
