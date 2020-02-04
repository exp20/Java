package com.mycomp.model.entity;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity (name = "Doctor")
@Table(name = "\"doctors\"", schema = "\"PUBLIC\"")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Doctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "\"id\"")
    @XmlElement
    private long id;


    @Column (name = "\"name\"")
    @XmlElement
    private String name;


    @Column (name = "\"last_name\"")
    @XmlElement
    private String last_name;


    @Column (name = "\"patronymic\"")
    @XmlElement
    private String patronymic;


    @Column (name = "\"specialization\"")
    @XmlElement
    private String specialization;


    public Doctor(){

    }
    public Doctor(String name, String last_name, String patronymic, String specialization){
        this.name=name;
        this.last_name=last_name;
        this.patronymic=patronymic;
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
    public String getPatronymic(){
        return patronymic;
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
    public void setPatronymic(String honestly){
        this.patronymic = honestly;
    }
    public void setSpecialization(String specialization){
        this.specialization=specialization;
    }



    public String toString(){
        return new String("doctor[ "+id+", "+name+", "+last_name+", "+patronymic+", "+specialization+"]");
    }
}
