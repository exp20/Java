package com.mycom.entity;


import javax.persistence.*;

@Entity
@Table (name = "\"doctors\"", schema = "\"PUBLIC\"")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "\"id\"")

    private long id;
    @Column (name = "\"name\"")
    private String name;
    @Column (name = "\"last_name\"")
    private String last_name;
    @Column (name = "\"honestly\"")
    private String honestly;
    @Column (name = "\"specialization\"")
    private String specialization;

    public Doctor(){

    }
    public Doctor(String name, String last_name, String honestly, String specialization){
        this.name=name;
        this.last_name=last_name;
        this.honestly=honestly;
        this.specialization=specialization;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }
    public String getLast_name(){
        return last_name;
    }
    public String getHonestly(){
        return honestly;
    }
    public String getSpecialization(){
        return specialization;
    }

    public void setName (String name){
        this.name=name;
    }
    public void setLast_name(String last_name){
        this.last_name = last_name;
    }
    public void setHonestly(String honestly){
        this.honestly = honestly;
    }
    public void setSpecialization(String specialization){
        this.specialization=specialization;
    }

    public String toString(){
        return new String("doctor[ "+id+", "+name+", "+last_name+", "+honestly+", "+specialization+"]");
    }
}
