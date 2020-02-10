package com.mycomp.model.dao;

import com.mycomp.model.entity.EmailHistory;

import java.util.List;

public interface EmailHistoryDAOInterface {
    //CRUD
    long add(EmailHistory emailHistory);
    List<EmailHistory> getAll();
    EmailHistory findById(long id);
  //  void update(EmailHistory emailHistory);
    void delete(EmailHistory emailHistory);
}
