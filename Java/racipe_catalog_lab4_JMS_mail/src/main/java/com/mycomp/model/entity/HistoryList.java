package com.mycomp.model.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "HistoryList")
@XmlAccessorType(XmlAccessType.FIELD)
public class HistoryList {

        @XmlElement(name = "history")
        private List<History> historyList;

        public HistoryList(List<History> historyList){
            this.historyList = historyList;
        }

        public HistoryList(){
        }

        public List<History> getHistoryList() {
            return historyList;
        }

        public void setHistoryList(List<History> historyList) {
            this.historyList = historyList;
        }

}

