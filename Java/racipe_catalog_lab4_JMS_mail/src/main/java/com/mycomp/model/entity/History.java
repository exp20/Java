package com.mycomp.model.entity;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@Entity (name = "History")
@Table(name = "HISTORY", schema = "PUBLIC")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class History implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @XmlElement
    private long id;
    @XmlElement
    @Column(name = "CHANGETYPE")
    private String changeType;

    @XmlElement
    @Column(name = "CHANGEENTITY")
    private String changeEntity;

    @XmlElement
    @Column(name = "VALUE")
    private String value;

    public History(){
    }

    public History(String value, String changeType, String changeEntity){
        this.value = value;
        this.changeType = changeType;
        this.changeEntity = changeEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getChangeEntity() {
        return changeEntity;
    }

    public void setChangeEntity(String changeEntity) {
        this.changeEntity = changeEntity;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Changed entity " + changeEntity + " changed value " + value + " changed action " + changeType;
    }
}