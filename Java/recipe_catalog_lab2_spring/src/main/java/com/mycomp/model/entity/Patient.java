package com.mycomp.model.entity;

import javax.persistence.*;


@Entity
@Table (name = "\"patients\"", schema = "\"PUBLIC\"")
public class Patient {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column (name = "\"id\"")
    private long id;
    @Column (name = "\"name\"")
    private String name;
    @Column (name = "\"patronymic\"")
    private String patronymic;
    @Column (name = "\"last_name\"")
    private String last_name;
    @Column (name = "\"phone\"")
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
