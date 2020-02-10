package com.mycomp.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "EmailHistory")
@Table(name = "EMAILHISTORY", schema = "\"PUBLIC\"")
public class EmailHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String email;

    @Column
    private String condition;

    public EmailHistory(){
    }

    public EmailHistory(String email, String condition){
        this.email = email;
        this.condition = condition;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Email history: email: " + email + " condition: " + condition;
    }
}