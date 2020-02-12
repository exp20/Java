package com.mycomp.model.entity;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement (name = "EmailHistoryList")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmailHistoryList {

    @XmlElement(name = "email_history")
    private List<EmailHistory> emailHistory;

    public EmailHistoryList(List<EmailHistory> emailHistory){
        this.emailHistory = emailHistory;
    }

    public EmailHistoryList(){
    }

    public List<EmailHistory> getEmailHistory() {
        return emailHistory;
    }

    public void setEmailHistory(List<EmailHistory> emailHistory) {
        this.emailHistory = emailHistory;
    }

}
