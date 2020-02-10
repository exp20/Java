package com.mycomp.model.dao;

import com.mycomp.model.entity.History;

import java.util.List;

public interface HistoryDAOInterface {
    //CRUD
    long add(History doctor);
    List<History> getAll();
    History findById(long id);
 //   void update(History doctor);
    void delete(History doctor);
}

