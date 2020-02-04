package com.mycomp.model.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Entity (name = "Patient")
@Table (name = "\"patients\"", schema = "\"PUBLIC\"")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Patient {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column (name = "\"id\"")
    @XmlElement
    private long id;

    @Column (name = "\"name\"")
    @XmlElement
    private String name;

    @Column (name = "\"patronymic\"")
    @XmlElement
    private String patronymic;

    @Column (name = "\"last_name\"")
    @XmlElement
    private String last_name;

    @Column (name = "\"phone\"")
    @XmlElement
    private String phone;

    public Patient(){

    }
    public Patient(String name, String last_name, String patronymic, String phone){
        this.name=name;
        this.last_name=last_name;
        this.patronymic=patronymic;
        this.phone=phone;
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
    public String getPhone(){
        return phone;
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
    public void setPhone(String phone){
        this.phone=phone;
    }

    public String toString(){
        return new String("patient[ "+id+", "+name+", "+last_name+", "+patronymic+", "+phone+"]");
    }
}
