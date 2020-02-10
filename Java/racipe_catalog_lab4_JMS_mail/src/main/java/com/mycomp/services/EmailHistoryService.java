package com.mycomp.services;

import com.mycomp.model.entity.EmailHistory;

import java.util.List;

public interface EmailHistoryService {

        public long add(EmailHistory history) throws Exception;

        public List<EmailHistory> getAll() throws Exception;

        public EmailHistory findById(long id) throws Exception;

     //   public void update(EmailHistory history) throws Exception;

        public void delete(EmailHistory history) throws Exception;

}
